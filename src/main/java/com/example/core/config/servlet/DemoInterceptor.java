package com.example.core.config.servlet;

import com.example.core.config.security.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class DemoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("DemoInterceptor ---------------- preHandle");
//        MDC.put("userId", "swi");
//        MDC.put("ipAddress", request.getRemoteAddr());

        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("ses");
        log.info("DemoInterceptor - user ---------------- {}", user);
        if( user != null  ) {
            ClientHolder.setUserInfo(user);
        }

   		return true;
   	}

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("DemoInterceptor ---------------- postHandle");
   	}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("DemoInterceptor ---------------- afterCompletion");
   	}
}
