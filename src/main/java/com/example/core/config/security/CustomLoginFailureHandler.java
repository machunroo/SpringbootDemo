package com.example.core.config.security;

import com.example.core.model.RestResult;
import com.example.core.util.HttpUtil;
import com.example.core.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		boolean isWrongPw = false;
		String message = null;
		String clientIpAddress = HttpUtil.getClientIpAddress(request);

		if (e.getCause() instanceof Exception) {
			message = "아이디 또는 비밀번호를 확인해 주시기 바랍니다.";
			isWrongPw = true;
		} else if (e instanceof BadCredentialsException) {
			message = "아이디 또는 비밀번호를 확인해 주시기 바랍니다.";
			isWrongPw = true;
		} else if (e instanceof SessionAuthenticationException) {
			message = "이미 사용중인 아이디입니다."; // Maximum sessions of 1 for this principal exceeded
		} else if (e.getMessage().equals("wrongCountOver")) {
			message = "아이디 또는 비밀번호 오류 횟수가 초과 되었습니다.";
		}  else {
			message = "시스템 오류가 발생했습니다. 관리자에게 문의해 주시기 바랍니다.";
		}

		// 아이디/비밀번호 오류시 비밀번호 오류 횟수 업데이트
		if( isWrongPw ) {
			String userId = request.getParameter("userId");
			String gateCd = request.getParameter("gateCd");
		}

		log.info("authentication failure. (message: {})", message);

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(JsonUtil.toString(new RestResult(false, message, null)));
	}

}