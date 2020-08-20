package com.stone.equipsys.manage.Action;

import com.stone.equipsys.core.common.exception.ImgCodeException;

public class AuthAction {

	public static void checkImgCode(String code,Object object) {
		if(!code.equals(object)) {
			throw new ImgCodeException("验证码错误");
			
		}
	}
}
