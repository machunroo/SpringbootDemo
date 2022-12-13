package com.example.demo.controller;

import com.example.core.config.security.UserInfo;
import com.example.core.config.servlet.ClientHolder;
import com.example.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class DemoLoginController {

    @Autowired DemoService service;

    @GetMapping(value = "/login")
    public String login(Model model, HttpServletRequest request) {
        return "login";
    }

    @GetMapping("/main")
    public String main(Model model, HttpServletRequest request) {
        UserInfo user = ClientHolder.getUserInfo();
        System.out.println("DemoLoginController[main]: " + user.getLoginId());
        System.out.println("DemoLoginController[main]: " + user.getPassword());
        return "main";
    }

}
