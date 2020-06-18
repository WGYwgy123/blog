package com.wgy.blog.config;

import com.wgy.blog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //所有的WebMvcConfigurer会一起起作用
    @Bean //将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer = new WebMvcConfigurer(){
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //解除静态资源的拦截
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/static/");;
            }
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/admin/**")
                        .excludePathPatterns("/admin")
                        .excludePathPatterns("/admin/login");

            }
        };
        return configurer;
    }


}
