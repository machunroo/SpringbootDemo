package com.example.demo.service;

import com.example.core.config.security.UserInfo;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.mapper.DemoUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DemoUserInfoService {
    @Autowired private DemoUserMapper mapper;

    public UserInfo selectUser(String userId) {
        return mapper.selectUser(userId);
    }

}
