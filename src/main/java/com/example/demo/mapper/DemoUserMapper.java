package com.example.demo.mapper;

import com.example.core.config.security.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DemoUserMapper {
    public UserInfo selectUser(String userId);
}
