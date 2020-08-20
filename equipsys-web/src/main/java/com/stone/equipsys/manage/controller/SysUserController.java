package com.stone.equipsys.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.RdPage;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.SysUserRole;
import com.stone.equipsys.core.mapper.OrgDeptMapper;
import com.stone.equipsys.core.mapper.SysRoleMapper;
import com.stone.equipsys.core.mapper.SysUserMapper;
import com.stone.equipsys.core.mapper.SysUserRoleMapper;
import com.stone.equipsys.core.model.SysUserModel;
import com.stone.equipsys.core.service.SysUserRoleService;
import com.stone.equipsys.core.service.SysUserService;
import com.stone.equipsys.manage.Action.SysUserAction;
import com.stone.equipsys.manage.TreeUtil.RoleUtile;

@Controller
@RequiresPermissions("sys:admin")
public class SysUserController {

	@Resource
	private SysUserService userservice;
	@Resource
	private SysUserRoleService UserRoleservice;
	@Resource
	private SysUserRoleMapper UserRoleMapper;
	@Resource
	private SysUserMapper UserMapper;
	@Resource
	private OrgDeptMapper deptmapper;
	@Resource
	private SysRoleMapper rolemapper;
	
	@RequestMapping(value="system/usermanage")
	public String gotoSysUserManage(HttpServletResponse response, HttpServletRequest request) {
		Page<SysUserModel> userlist=userservice.getUserExtList(null,1,12);
		request.setAttribute("userlist", userlist);
		request.setAttribute("page", new RdPage(userlist).getPageStr("getUserPage"));
		return PathConstant.UserManage;
	}
	@RequestMapping(value="system/searchUser")
	public String search(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="username",defaultValue="")String username,
			@RequestParam(value="employee",defaultValue="")String employee,
			@RequestParam(value="current",defaultValue="1")int current,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize) {
		HashMap<String, Object> params=new HashMap<String,Object>();
		params.put("lusername", username);
		params.put("ldeptname", employee);
		Page<SysUserModel> userlist=userservice.getUserExtList(params,current,pageSize);
		request.setAttribute("userlist", userlist);
		request.setAttribute("page", new RdPage(userlist).getPageStr("getUserPage"));
		return PathConstant.UserList;
	}
	@RequestMapping(value="system/findUserById")
	public void findSysUserById(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		SysUserModel user=userservice.findUserExtById(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(user!=null) {
			res.put("user", user);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/saveUser")
	@RequiresPermissions("sys:user:save")
	public void save(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="userinfoname")String username,@RequestParam(value="userinfoid",defaultValue="")String userid) {
		Map<String, Object> res = new HashMap<String, Object>();
		System.out.println("username="+username);
		if("".equals(userid)) {
			if(UserMapper.countUserByUserName(username)>0) {
				res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
				res.put(Constant.RESPONSE_CODE_MSG, "用户名已存在");
			}else {
				int n=userservice.saveUserInfo(request);
				if(n==1) {
					res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
					res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
				}else {
					res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
					res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
				}				
			}
		}
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping(value="system/deleteUser")
	@RequiresPermissions("sys:user:manage")
	public void delete(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		boolean flag=userservice.deleteUser(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(flag) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/resetPassword")
	@RequiresPermissions("sys:user:manage")
	public void resetPassword(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		int n=userservice.resetPassword(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(n==1) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "重置密码成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "重置密码失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/setPassword")
	public void setPassword(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		int n=userservice.setPassword(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(n==1) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/setUserRole")
	@RequiresPermissions("sys:user:manage")
	public void setUserRole(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id,@RequestParam(value="rolestr")String rolestr) {
		boolean n=UserRoleservice.setUserRole(id,rolestr);
		Map<String, Object> res = new HashMap<String, Object>();
		if(n) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/clockUser")
	@RequiresPermissions("sys:user:manage")
	public void clockUser(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id,@RequestParam(value="type")int type) {
		HashMap<String, Object> param=new HashMap<>();
		param.put("id", id);
		System.out.println("type===="+type);
		int	staus=type==1?2:1;
		param.put("staus", staus);
		int n=UserMapper.updateSelective(param);
		Map<String, Object> res = new HashMap<String, Object>();
		if(n==1) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "用户锁定成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "用户锁定成功");
		}
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping(value="system/getUserRole")
	public void getUserRole(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		String roletreejson=JSONObject.toJSONString(RoleUtile.RoleeTreeList(deptmapper.findByParentDept(), rolemapper.listSelective(null)));
		HashMap<String, Object> param=new HashMap<String,Object>();
		param.put("userId", id);
		List<SysUserRole> userrole=UserRoleMapper.listSelective(param);
		Map<String, Object> res = new HashMap<String, Object>();
		if(roletreejson!=null) {
			res.put("roletree", roletreejson);
			res.put("userrole",userrole);
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/setAdmin")
	@RequiresPermissions("sys:admin")
	public void setAdmin(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id,@RequestParam(value="type")int type) {
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("id", id);
		param.put("isadmin", type==1?0:1);
		int n=UserMapper.updateSelective(param);
		Map<String, Object> res = new HashMap<String, Object>();
		if(n>0) {
			res.put(Constant.RESPONSE_CODE_MSG, "设置成功");
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "设置失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
