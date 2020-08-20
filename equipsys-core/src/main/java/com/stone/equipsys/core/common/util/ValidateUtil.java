package com.stone.equipsys.core.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tool.util.StringUtil;

public class ValidateUtil
{

	private static Map<String, Object> map;
	
	/**
	 * 用户名是否有效
	 * @param user_name
	 * @return
	 */
	public static boolean isUser_name(String user_name)
	{
		Pattern p = Pattern.compile("^(?![0-9]+$)[0-9A-Za-z\u0391-\uFFE5]{2,15}$");
		Matcher m = p.matcher(user_name);
		return m.matches();
	}
	/**
	 * 是否包含中文
	 * @param str
	 * @return
	 */
	public static boolean isChinese(String str)
	{
		String str_ = StringUtil.isNull(str);
		Pattern regex = Pattern.compile("[\\u4e00-\\u9fa5]{2,25}");
		Matcher matcher = regex.matcher(str_);

		return matcher.matches();
	}
	/**
	 * 	校验邮箱
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email)
	{
		String email_ = StringUtil.isNull(email);
		Pattern regex = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = regex.matcher(email_);
		return matcher.matches();
	}
	/**
	 * 	校验手机号码
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone)
	{
		String phone_ = StringUtil.isNull(phone);
		Pattern regex = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher matcher = regex.matcher(phone_);
		boolean isMatched = matcher.matches();
		return isMatched;
	}
	/**
	 *	 判断手机号码内容
	 * @param phone
	 * @return
	 */
	public static Map<String, Object> checkPhone(String phone)
	{
		map = new HashMap<String, Object>();
		map.put("result", false);
		if (StringUtil.isBlank(phone))
		{
			map.put("message", "请输入手机号！");
			return map;
		}
		if (!isPhone(phone))
		{
			map.put("message", "请输入正确的11位手机号！");
		} else
		{
			map.put("result", true);
		}
		return map;
	}
}
