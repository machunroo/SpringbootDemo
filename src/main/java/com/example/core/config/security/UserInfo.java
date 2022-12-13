package com.example.core.config.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String loginId;
    private String password;
    private String userType;
}
