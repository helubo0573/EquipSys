package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.exception.ParamValideException;
import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.SysUserMapper;
import com.stone.equipsys.core.mapper.SysUserRoleMapper;
import com.stone.equipsys.core.model.SysUserModel;
import com.stone.equipsys.core.domain.SysMenu;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.service.SysUserService;

/**
 * 	系统用户表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @param <SysUserAction>
 * @date 2020-05-28 19:18:59
 */
 
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {
	
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
   
    @Resource
    private SysUserMapper UserMapper;
    @Resource
    private SysUserRoleMapper UserRoleMapper;
    @Override
	public BaseMapper<SysUser, Long> getMapper() {
		return UserMapper;
	}

	@Override
	public void editUserPassWord(SysUser op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SysUserModel getUserByUserName(String userName) {
		HashMap<String, Object> param=new HashMap<>();
		param.put("username", userName);
		return UserMapper.getUserExtList(param).get(0);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editUserLoginInfo(SysUser op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countUserByUserName(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countUserByMobile(String mobile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page<SysUserModel> getUserExtList(HashMap<String, Object> param, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<SysUserModel> userlist=UserMapper.getUserExtList(param);
		return (Page<SysUserModel>)userlist;
	}

	@Override
	public SysUserModel findUserExtById(Long id) {
		
		return UserMapper.findUserExtById(id);
	}

	@Override
	public int saveUserInfo(HttpServletRequest request) {
		int n=0;
		try {
			String id=request.getParameter("userinfoid");
			SysUser user=new SysUser(id,
					request.getParameter("userinfoname"),
					request.getParameter("userinfoempid"));
			if(id=="") {
				if(UserMapper.countUserByUserName(user.getUsername())>0) {
					throw new ParamValideException("用户名已存在");
				}
				user.setPassword(Constant.initPassword);
				user.setStaus(1);
				user.setIsadmin(0);
				n=UserMapper.save(user);
			}else {
				n=UserMapper.update(user);
			}
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	@Transactional
	public boolean deleteUser(Long id) {
		try {
			UserRoleMapper.deleteByUserId(id);
			UserMapper.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int resetPassword(Long id) {
		HashMap<String, Object> param=new HashMap<String,Object>();
		param.put("id", id);
		param.put("password", Constant.initPassword);
		int n=UserMapper.updateSelective(param);
		return n;
	}

	@Override
	public int setPassword(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}