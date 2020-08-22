package com.stone.equipsys.manage.Action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.stone.equipsys.core.common.exception.ParamValideException;
import com.stone.equipsys.core.common.exception.ServiceException;
import com.stone.equipsys.core.common.util.StringUtil;
import com.stone.equipsys.core.domain.EmployeeInfo;
import com.stone.equipsys.core.domain.OrgDept;
import com.stone.equipsys.core.domain.OrgPost;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.mapper.SysUserMapper;
import com.stone.equipsys.core.service.SysUserService;
import com.stone.equipsys.manage.TreeUtil.EmployeeUtil;

import tool.util.BeanUtil;

public class SysUserAction {
	
	@Resource
	private static SysUserMapper usermapper;
	/*
	public static boolean createUser(String username,String phone,String password) {
		SysUser user=new SysUser();
		user.setUsername(username);
		user.setMobile(phone);
		user.setPassword(password);
		user.setCreateTime(new Date());
		user.setStaus(0);
		int n=userinfosv.insert(user);
		return n==1?true:false;
	}
	public static void checkParam(String username,String password,String phone) throws ParamValideException, ServiceException {
		checkUsername(username);
		checkPhone(phone);
		checkPassword(password);
	}
	
	
	public static void checkPassword(String password) throws ParamValideException {
		if(StringUtil.isBlank(password)) {
			throw new ParamValideException("密码不能为空");
		}
	}
	
	public static void checkPhone(String phone) throws ParamValideException, ServiceException {
		if(StringUtil.isBlank(phone)) {
			throw new ParamValideException("手机号不能为空");
		}
		if(!StringUtil.isPhone(phone)) {
			throw new ParamValideException("手机号码格式不正确");
		}
		if(userinfosv.countUserByMobile(phone)>0) {
			throw new ParamValideException("手机号码已存在");
		}
	}
	*/
	
	public static int checkUsername(String username) {
		System.out.println("username111="+username);
		return usermapper.countUserByUserName(username);
	}
	
	public static String createEmployeeTree(List<OrgDept> deptlist,List<EmployeeInfo> emplist) {
		return JSONObject.toJSONString(EmployeeUtil.EmployeeTreeList(emplist, deptlist));
	}
	
	public static String createUnPostEmployeeTree(List<OrgDept> deptlist,List<EmployeeInfo> emplist) {
		return JSONObject.toJSONString(EmployeeUtil.UnPostEmployeeTreeList(emplist, deptlist));
	}
}
