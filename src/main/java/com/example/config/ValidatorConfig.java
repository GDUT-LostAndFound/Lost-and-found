package com.example.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

/**
 * 参数校验器配置
 *
 * @author DF.
 * @date 2023-05-20 23:14:67
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator(AutowireCapableBeanFactory springFactory) {
        try (ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 快速失败，validator默认遇到校验失败的也会把所有参数都进行一次校验，加这个就可以在遇到第一个失败时中断校验
                .failFast(true)
                // 解决 SpringBoot 依赖注入问题，可以在自定义的注解校验器中使用spring注入组件(不设置的话注入组件是无效的)
                .constraintValidatorFactory(new SpringConstraintValidatorFactory(springFactory))
                .buildValidatorFactory()) {
            return factory.getValidator();
        }
    }

}

