package com.example.demo.service;

import com.example.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DemoService {
    @Autowired private DemoMapper mapper;

    public List<Map<String, Object>> selectDemo1(Map<String, Object> param) {
        return mapper.selectDemo1(param);
    }

    public List<Map<String, Object>> selectDemo2() {
        return mapper.selectDemo2("testParameter", "@ParamTest");
    }
    public void insertDemo() {
        System.out.println("insertDemo");
        int cnt = mapper.insertDemo();
        System.out.println(cnt);
    }
    public void insertErrDemo() throws Exception {
        System.out.println("insertErrDemo");
        int cnt = mapper.insertDemo();
        System.out.println(cnt);
        throw new Exception();
    }

}
