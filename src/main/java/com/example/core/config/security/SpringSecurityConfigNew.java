package com.example.core.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfigNew {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Order(1)
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css", "/images");
    }

    @Bean
    @Order(2)
    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/main1").hasAuthority("ASS")
                    .antMatchers("/main2").hasAuthority("AAA")
                    .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .successHandler(new CustomLoginSuccessHandler())
                .failureHandler(new CustomLoginFailureHandler())
                .permitAll()
            // 스프링 시큐리티가 세션을 생성하지 않지만 기존에 존재하면 사용
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
            // 동일한 도메인에서 iframe 접근 가능하게 설정
            .and().headers().frameOptions().sameOrigin()
            .and()
                .logout()
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
        ;
        return http.build();
    }
}
