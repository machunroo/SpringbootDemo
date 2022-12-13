package com.example.core.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * WebSecurityConfigurerAdapter로 UserDetail 작성하고
 * SecurityFilterChain 방식으로 변경
 */
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
public class SpringSecurityConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler loginFailureHandler() {
//        return new CustomLoginFailureHandler();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler loginSuccessHandler() {
//        return new CustomLoginSuccessHandler();
//    }
//
////    @Override
////    public void configure(WebSecurity http) throws Exception {
////        http.ignoring().antMatchers("/css/**","/image/**");
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()	// csrf 토큰을 비활성화
//                    .authorizeRequests()
////                    .antMatchers("/","/login/**","/js/**","/css/**","/image/**").permitAll() // 해당 경로들은 접근을 허용
////                    .antMatchers("/admin").hasRole("ADMIN") // 괄호의 권한을 가진 유저만 접근가능, ROLE_가 붙어서 적용 됨. 즉, 테이블에 ROLE_권한명 으로 저장해야 함.
////                    .antMatchers("/user").hasRole("USER")
////                    .antMatchers("/member").hasRole("MEMBER")
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login/process") // 로그인 처리
//                        .usernameParameter("loginId")
//                        .passwordParameter("password")
//                        .successHandler(loginSuccessHandler())
//                        .failureHandler(loginFailureHandler())
//                        .permitAll()
//                .and()
//                    .logout()
//                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
//        ;
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*")); //
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "TOKEN_ID", "X-Requested-With", "Authorization", "Content-Type", "Content-Length", "Cache-Control"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

}
