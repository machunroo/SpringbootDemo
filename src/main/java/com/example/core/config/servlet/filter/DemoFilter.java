package com.example.core.config.servlet.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class DemoFilter  implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init MyFilter");
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //다음 Filter 실행 전 처리 (preHandle)
        log.debug("DemoFilter=============================================> 11111111111111111");

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        //다음 filter-chain에 대한 실행 (filter-chain의 마지막에는 Dispatcher servlet실행)
        filterChain.doFilter(servletRequest, servletResponse);

        //다음 Filter 실행 후 처리 (postHandle)
        log.debug("DemoFilter=============================================> 22222222222222222");
    }

    @Override
    public void destroy() {
        //filter 제거 시 처리 (보통 자원의 해제처리를 한다.)
    }
}
