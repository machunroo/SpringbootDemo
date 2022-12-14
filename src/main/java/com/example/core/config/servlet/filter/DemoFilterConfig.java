package com.example.core.config.servlet.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoFilterConfig {
    @Bean
    public FilterRegistrationBean<DemoFilter> demoFilterRegistration1(){
        FilterRegistrationBean<DemoFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DemoFilter());
        registrationBean.addUrlPatterns("/*"); // 여러개 지정시 "..",".." 형식
        registrationBean.setOrder(1);
        // registrationBean.setName("demo-filter1"); // 지정않하는 경우 beanName default
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<DemoFilter2> demoFilterRegistration2(){
        FilterRegistrationBean<DemoFilter2> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new DemoFilter2());
        registrationBean.addUrlPatterns("/main1");
        registrationBean.setOrder(2);
        // registrationBean.setName("demo-filter2"); // 지정않하는 경우 beanName default
        return registrationBean;
    }
}
