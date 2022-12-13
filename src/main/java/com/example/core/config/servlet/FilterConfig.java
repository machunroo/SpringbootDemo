package com.example.core.config.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class FilterConfig {

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
