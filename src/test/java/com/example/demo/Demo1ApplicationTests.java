package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Demo1ApplicationTests {

    @Autowired
    PasswordEncoder encoder;

    @Test
    void contextLoads() {
        String passwd = "@1234";
        System.out.println(encoder.encode(passwd));
    }

}
