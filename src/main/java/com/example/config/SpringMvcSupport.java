package com.example.config;


import com.example.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//设置和请求有关的内容
@Configuration
public class SpringMvcSupport
        implements WebMvcConfigurer {
    @Autowired
    UserInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //忽略的拦截名单
        String[] urls = {
               "/api/login",
               "/api/register"
        };
        //自定义用户拦截器注册，弃用
        registry.addInterceptor(loginInterceptor)
                //拦截
                .addPathPatterns("/**")
                //放行
                .excludePathPatterns(urls);


    }
}