package com.example.demo.service;

import com.example.core.config.security.CustomUserDetails;
import com.example.core.config.security.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final DemoUserInfoService demoUserInfoService;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername ==============>");

        UserInfo user = demoUserInfoService.selectUser(loginId);
        System.out.println("UserDetailsServiceImpl user ==============>" + user);
        System.out.println("UserDetailsServiceImpl user.getLoginId() ==============>" + user.getLoginId());
        System.out.println("UserDetailsServiceImpl user.getPassword() ==============>" + user.getPassword());

        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        // 값 세팅 로직 시작
        // ...

        if( user == null ) {
            System.out.println("UsernameNotFoundException ==============>");
            throw new UsernameNotFoundException("");
        }

        System.out.println("customUserDetails.getUsername: " + customUserDetails.getUsername());
        // 값 세팅 로직 끝
        return customUserDetails;
    }
}