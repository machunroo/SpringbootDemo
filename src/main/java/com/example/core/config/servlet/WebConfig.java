package com.example.core.config.servlet;

import com.example.core.config.servlet.DemoInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DemoInterceptor())
//                .addPathPatterns("*");
                .excludePathPatterns("/css/**", "/images/**", "/js/**") // interceptor 제외 설정
                ;
    }

    @Bean
    public FilterRegistrationBean<DemoFilter> demoFilterRegistration(){
        FilterRegistrationBean<DemoFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DemoFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        registrationBean.setName("demo-filter");
        return registrationBean;
    }

}
