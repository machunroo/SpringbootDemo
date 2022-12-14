package com.example.core.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CustomUserDetails  implements UserDetails, Serializable {

    private static final long serialVersionUID = 174726374856727L;

    private final UserInfo user; // 사용자 정보
    private final Collection<GrantedAuthority> authorities;	//권한 목록

    public CustomUserDetails(UserInfo user) {
        this.user = user;
        this.authorities = new ArrayList<>();
        addAuthority(user.getUserType());
    }

    public UserInfo getUserInfo() {
   		return user;
   	}

    public void addAuthority(String role) {
   		if (role != null) {
   			this.authorities.add(new SimpleGrantedAuthority(role));
   		}
   	}

    /**
    * 해당 유저의 권한 목록
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
           return authorities;
    }

	/**
    * 비밀번호
    */
	@Override
    public String getPassword() {
        return user.getPassword();
    }


	/**
    * PK값
    */
    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    /**
     * 계정 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김 여부
     * true : 잠기지 않음
     * false : 잠김
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 비밀번호 만료 여부
     * true : 만료 안됨
     * false : 만료
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * 사용자 활성화 여부
     * ture : 활성화
     * false : 비활성화
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}