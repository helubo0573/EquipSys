package com.stone.equipsys.core.system.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.stone.equipsys.core.common.service.Crypto;



public class PasswordEncoder extends BasePasswordEncoder
{

	private Crypto crypto;

	private SaltSource saltSource;

	private Object getSalt(){
		UserDetails user = null;
		if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null){
			user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		return saltSource.getSalt(user);
	}

	public void setCrypto(Crypto crypto){
		this.crypto = crypto;
	}

	public void setSaltSource(SaltSource saltSource){
		this.saltSource = saltSource;
	}

	public String encodePassword(String rawPass) throws DataAccessException{
		System.out.println("encodePassword="+ rawPass);
		return encodePassword(rawPass, getSalt());
	}

	public String encodePassword(String rawPass, Object salt) throws DataAccessException{
		if (salt == null){
			salt = getSalt();
		}
		String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
		System.out.println("saltedPass="+saltedPass+"-rawPass="+rawPass+"--salt="+salt);
		return crypto.encrypt(saltedPass);
	}

	public boolean isPasswordValid(String encPass, String rawPass) throws DataAccessException{

		return isPasswordValid(encPass, rawPass, getSalt());
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException{

		if (salt == null) salt = getSalt();
		String pass1 = "" + encPass;
		String pass2 = encodePassword(rawPass, salt);
		System.out.println("密码-"+rawPass+"--"+pass2);
		return pass1.equals(pass2);
	}

}
