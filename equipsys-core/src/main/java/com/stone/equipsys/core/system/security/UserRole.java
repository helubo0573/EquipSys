package com.stone.equipsys.core.system.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserRole extends User
{

	private static final long serialVersionUID = -1548996487718223988L;

	private Map<String, UserFunction> functionMap;

	public UserRole(String username, String password, Collection<GrantedAuthority> authorities, Map<String, UserFunction> functionMap)
	{
		super(username, password, authorities);
		if (functionMap != null)
		{
			this.functionMap = functionMap;
		} else
		{
			this.functionMap = new HashMap<String, UserFunction>();
		}
	}

	public Map<String, UserFunction> getFunctionMap()
	{
		return Collections.unmodifiableMap(functionMap);
	}

}
