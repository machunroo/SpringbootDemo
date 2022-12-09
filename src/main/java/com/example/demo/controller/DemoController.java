package com.example.demo.controller;

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
public class DemoController {

    /*
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Tue Dec 06 15:40:36 KST 2022
There was an unexpected error (type=Not Found, status=404).
No message available
     */


    @Autowired DemoService service;

    @GetMapping("/main1")
    public String main(Model model, HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        param.put("testParameter", "테스트");
        List<Map<String, Object>> list = service.selectDemo1(param);
        request.setAttribute("demo", list);

        log.debug("[methdo:{}] userId: {}, ip address: {}", "main1", MDC.get("userId"), MDC.get("ipAddress"));

        return "index";
    }

    // String 파라미터 전달
    @GetMapping("/main2")
    public String main2(Model model, HttpServletRequest request) {
        List<Map<String, Object>> list = service.selectDemo2();
        request.setAttribute("demo", list);

        log.debug("[methdo:{}] userId: {}, ip address: {}", "main2", MDC.get("userId"), MDC.get("ipAddress"));
        return "index";
    }

    @GetMapping("/insert")
    public void insert(Model model, HttpServletRequest request) {
        log.debug("[methdo:{}] userId: {}, ip address: {}", "insert", MDC.get("userId"), MDC.get("ipAddress"));
        service.insertDemo();
    }

    @GetMapping("/insert/err")
    public void insertErr(Model model, HttpServletRequest request) throws Exception {
        log.debug("[methdo:{}] userId: {}, ip address: {}", "insertErr", MDC.get("userId"), MDC.get("ipAddress"));
        service.insertErrDemo();
    }

}
