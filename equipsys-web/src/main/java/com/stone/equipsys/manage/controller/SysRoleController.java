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
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.Tree;
import com.stone.equipsys.core.domain.SysRole;
import com.stone.equipsys.core.domain.SysRoleMenu;
import com.stone.equipsys.core.mapper.OrgDeptMapper;
import com.stone.equipsys.core.mapper.SysRoleMapper;
import com.stone.equipsys.core.mapper.SysRoleMenuMapper;
import com.stone.equipsys.core.service.OrgDeptService;
import com.stone.equipsys.core.service.SysMenuService;
import com.stone.equipsys.core.service.SysRoleMenuService;
import com.stone.equipsys.core.service.SysRoleService;
import com.stone.equipsys.manage.TreeUtil.RoleUtile;

@Controller
@RequiresPermissions("sys:admin")
public class SysRoleController {

	@Resource
	private OrgDeptService deptservice;
	@Resource
	private OrgDeptMapper deptmapper;
	@Resource
	private SysRoleMapper rolemapper;
	@Resource
	private SysRoleService roleservice;
	@Resource
	private SysRoleMenuMapper RoleMenuMapper;
	@Resource
	private SysRoleMenuService RoleMenuService;
	@Resource
	private SysMenuService MenuService;
	@RequestMapping(value="system/RoleManage")
	public String gotoSysRoleManage(HttpServletResponse response, HttpServletRequest request) {
		
		return PathConstant.RoleManage;
	}
	
	@RequestMapping(value="system/getRoleTree")
	public void getRoleTree(HttpServletResponse response, HttpServletRequest request) {
		String roletreejson=JSONObject.toJSONString(RoleUtile.RoleeTreeList(deptmapper.findByParentDept(), rolemapper.listSelective(null)));
		Map<String, Object> res = new HashMap<String, Object>();
		if(roletreejson!=null) {
			res.put("roletree", roletreejson);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping(value="system/saveRole")
	@RequiresPermissions("sys:role:save")
	public void saveRole(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="roleid")String roleid,
			@RequestParam(value="deptid",defaultValue="0")int deptid,
			@RequestParam(value="rolename")String rolename,
			@RequestParam(value="remarks")String remarks) {
		int n=0;
		SysRole role=new SysRole();
		role.setRoleName(rolename);
		role.setDeptId(deptid);
		role.setRemarks(remarks);
		if("".equals(roleid)){
			n=rolemapper.save(role);
		}else {
			role.setId(Long.parseLong(roleid));
			n=rolemapper.update(role);
		}
		Map<String, Object> res = new HashMap<String, Object>();
		if(n>0) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/deleteRole")
	public void deleteRole(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		boolean s=roleservice.deleteRoleById(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(s) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping(value="system/getRoleMenu")
	public void getRoleTMenu(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="roleid")Long roleid) {
		HashMap<String,Object> rolemap=new HashMap<String,Object>();
		rolemap.put("roleId", roleid);
		List<SysRoleMenu> rolemenulist=RoleMenuMapper.listSelective(rolemap);
		String treejson=Tree.TreeJson(MenuService.getMenu(), "id", "menuName", "parentId", "remarks");
		Map<String, Object> res = new HashMap<String, Object>();
		if(treejson!=null) {
			res.put("menutree", treejson);
			res.put("rmlist", rolemenulist);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping(value="system/setRoleMenu")
	@RequiresPermissions("sys:role:setmenu")
	public void setRoleTMenu(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id,@RequestParam(value="nodes")String nodes) {
		boolean Flag=RoleMenuService.setRoleMenu(id,nodes);
		Map<String, Object> res = new HashMap<String, Object>();
		if(Flag) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "设置成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "设置失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
