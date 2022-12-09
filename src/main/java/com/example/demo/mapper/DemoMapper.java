package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DemoMapper {
    public List<Map<String, Object>> selectDemo1(Map<String, Object> param);
//    public List<Map<String, Object>> selectDemo2(@Param("testParameter") String testParameter, @Param("annotationParam") String annotationParam);
    public List<Map<String, Object>> selectDemo2(String testParameter, String annotationParam);
    public int insertDemo();
}
