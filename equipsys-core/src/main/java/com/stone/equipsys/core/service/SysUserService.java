package com.stone.equipsys.core.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.SysMenu;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.model.SysUserModel;

/**
 * 系统用户表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:18:59
 */
public interface SysUserService extends BaseService<SysUser, Long>{

	void editUserPassWord(SysUser op);

	SysUserModel getUserByUserName(String userName);

	List<String> queryAllPerms(Long userId);	//查询所有授权

	void editUserLoginInfo(SysUser op);
	
	int countUserByUserName(String username);
	
	int countUserByMobile(String mobile);

	Page<SysUserModel> getUserExtList(HashMap<String, Object> param, int currentPage, int pageSize);

	/**用userid查找用户扩展信息*/
	SysUserModel findUserExtById(Long id);

	int saveUserInfo(HttpServletRequest request);

	boolean deleteUser(Long id);

	int resetPassword(Long id);

	int setPassword(Long id);

}
