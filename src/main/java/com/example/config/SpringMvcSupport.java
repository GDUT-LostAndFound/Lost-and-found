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

    //设置静态资源访问过滤，当前类需要设置为配置类，并被扫描加载
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //当访问/pages/????时候，从/pages目录下查找内容
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");

        ;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //忽略的拦截名单
        String[] urls = {

        };
        //自定义用户拦截器注册，弃用
        registry.addInterceptor(loginInterceptor)
                //拦截
                .addPathPatterns("/**")
                //放行
                .excludePathPatterns(urls);


    }
}