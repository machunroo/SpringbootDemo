package com.example.core.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		CustomUserDetails userDetail = (CustomUserDetails)auth.getPrincipal();
		UserInfo user = userDetail.getUserInfo();

		System.out.println("CustomSuccessHandler SuccessSuccessSuccessSuccess 111111111111111111111111111");
		System.out.println("user.getLoginId(): " + user.getLoginId());
		System.out.println("user.getPassword(): " + user.getPassword());
		System.out.println("user.getUserType(): " + user.getUserType());

		Collection<? extends GrantedAuthority> auths = auth.getAuthorities();
		for( GrantedAuthority  g : auths ) {
			System.out.println("user authority: " + g.getAuthority());
		}

		System.out.println("CustomSuccessHandler SuccessSuccessSuccessSuccess 222222222222222222222222222");



		response.sendRedirect("/main");
	}
}
