package com.stone.equipsys.core.system.security;

import org.springframework.security.core.GrantedAuthority;

public class MGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -8571395610763203893L;
	// 权限信息
    private String authority;
    
    /**
     * 构造函数
     * 
     * @param authority
     */
    public MGrantedAuthority(String authority) {
        this.authority = authority;
    }
    
	@Override
	public String getAuthority() {
		return authority;
	}

}
