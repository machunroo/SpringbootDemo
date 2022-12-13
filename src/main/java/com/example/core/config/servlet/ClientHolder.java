package com.example.core.config.servlet;

import com.example.core.config.security.UserInfo;

public class ClientHolder {
	private static final ThreadLocal<UserInfo> UserInfo = new ThreadLocal<UserInfo>(); // 로그인 정보

	public static void clear() {
		UserInfo.remove();
	}

	public static UserInfo getUserInfo() {
		return UserInfo.get() != null ? UserInfo.get() : null;
	}
	public static void setUserInfo(UserInfo UserInfo) {
		ClientHolder.UserInfo.set(UserInfo);
	}
}
