package com.stone.equipsys.core.system.security;

import org.apache.shiro.authc.UsernamePasswordToken;

@SuppressWarnings("serial")
public class UserToken extends UsernamePasswordToken{

	private String loginType;

    public UserToken(final String username, final String password,String loginType) {
        super(username,password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
