package com.gzcc.kindmanager;

import com.gzcc.kindmanager.config.SystemInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 @Autor zhuoyj[hopnetworks]
 @Date 2018/8/11
 @function
 @Description 用于登录拦截

 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                new SystemInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/static/**"). excludePathPatterns("/index").
                excludePathPatterns("/login").excludePathPatterns("/register")
        ;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/index").addResourceLocations("classpath:/static/index.html");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
//    @Bean
//    public ViewResolver viewResolver() {
//
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/resources/");
//      //  viewResolver.setSuffix(".html");
//        return viewResolver;
//    }
}