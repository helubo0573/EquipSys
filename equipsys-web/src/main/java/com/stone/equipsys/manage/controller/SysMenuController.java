package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.Tree;
import com.stone.equipsys.core.domain.SysMenu;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.service.SysMenuService;

@Controller
@RequiresPermissions("sys:admin")
public class SysMenuController{

	@Resource
	private SysMenuService MenuService;
	
	@RequestMapping(value="system/menumanage")
	public String toMenuPage(HttpServletResponse response, HttpServletRequest request) {
		return PathConstant.MenuManage;
	}
	
	@RequestMapping(value="system/getmenutree")
	public void getMenuTree(HttpServletResponse response, HttpServletRequest request) {
		String treejson=Tree.TreeJson(MenuService.getMenu(), "id", "menuName", "parentId", "remarks");
		Map<String, Object> res = new HashMap<String, Object>();
		if(treejson!=null) {
			res.put("menutree", treejson);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/getmenuinfo")
	public void getMenuInfo(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		SysMenu menu=MenuService.getById(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(menu!=null) {
			res.put("menu", menu);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping(value="system/selectparent")
	public void selectParentrMenu(HttpServletResponse response) {
		String menutreejson=Tree.TreeJson(MenuService.getParentMenu(), "id", "menuName", "parentId", "remarks");
		Map<String, Object> res = new HashMap<String, Object>();
		if(menutreejson!=null) {
			res.put("menutree", menutreejson);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="system/savemenu")
	@RequiresPermissions("sys:menu:save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="id")String id,
			@RequestParam(value="type")String type,
			@RequestParam(value="name")String name,
			@RequestParam(value="parent")String parent,
			@RequestParam(value="url")String url,
			@RequestParam(value="perms")String perms,
			@RequestParam(value="order")String order,
			@RequestParam(value="icon")String icon,
			@RequestParam(value="remarks")String remarks) {
		SysMenu menu=new SysMenu(id,type,name,parent,url,perms,order,icon,remarks);
		int n=0;
		if("".equals(id))
			n=MenuService.insert(menu);
		else
			n=MenuService.updateById(menu);
		Map<String, Object> res = new HashMap<String, Object>();
		if(n>0) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping(value="menu/getmenu")
	public void search(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		SysUser user=(SysUser) request.getSession().getAttribute("SysUser");
		List<SysMenu> menu=MenuService.getMenuByUserName(user);
		ServletUtils.writeToResponse(response, menu);
	}

	@RequestMapping(value="system/deletemenu")
	@RequiresPermissions("sys:menu:delete")
	public void delete(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		boolean flag=MenuService.deleteMenu(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(flag) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
