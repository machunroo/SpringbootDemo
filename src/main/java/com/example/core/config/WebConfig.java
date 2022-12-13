package com.example.core.config;

import com.example.core.config.servlet.DemoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")        // 허용대상 도메인
                .allowedMethods(
                    HttpMethod.GET.name(),
                    HttpMethod.POST.name(),
                    HttpMethod.PUT.name(),
                    HttpMethod.DELETE.name()
                )
                .allowCredentials(false)    // 크로스 도메인 요청시, 세션 고정 (for CORS Reqeusts)
                .maxAge((60 * 60 * 24))     // cache for 1 day
                // .allowedHeaders("x-requested-with, origin, content-type, accept")
        ;
    }

}
