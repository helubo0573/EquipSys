package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.JsonUtil;
import com.stone.equipsys.core.common.util.RdPage;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.EmployeeInfo;
import com.stone.equipsys.core.mapper.EmployeeInfoMapper;
import com.stone.equipsys.core.mapper.OrgDeptMapper;
import com.stone.equipsys.core.model.EmployeeInfoModel;
import com.stone.equipsys.core.model.SysUserModel;
import com.stone.equipsys.core.service.EmployeeInfoService;
import com.stone.equipsys.manage.Action.SysUserAction;

@Controller
@RequestMapping("employee")
@RequiresPermissions("sys:admin")
public class EmployeeController{

	@Resource
	private EmployeeInfoService employeeservice;
	@Resource
	private OrgDeptMapper deptmapper;
	@Resource
	private EmployeeInfoMapper employeemapper;
	
	@RequestMapping("/manage")
	public String toManage(HttpServletResponse response, HttpServletRequest request) {
		Page<EmployeeInfoModel> employee=employeeservice.searchExtEmployee(null, 1, 12);
		request.setAttribute("employee", employee);
		request.setAttribute("page", new RdPage(employee).getPageStr("getEmployeePage"));
		return PathConstant.EmployeeManage;
	}
	@RequestMapping("/save")
	@RequiresPermissions("org:employee:save")
	public void save(HttpServletResponse response, HttpServletRequest request) {
		Long id=employeeservice.saveEmployeeInfo(request);
		Map<String, Object> res = new HashMap<String, Object>();
		if(id!=-1) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping("/search")
	public String search(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="empname")String empname,
			@RequestParam(value="deptname")String deptname,
			@RequestParam(value="postname")String postname,
			@RequestParam(value="current",defaultValue="1")int current,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize)
			throws UnsupportedEncodingException, IOException {
		HashMap<String, Object> params=new HashMap<String,Object>();
		params.put("empname", empname);
		params.put("deptname", deptname);
		params.put("postname", postname);
		Page<EmployeeInfoModel> employee=employeeservice.searchExtEmployee(params, current, pageSize);
		request.setAttribute("employee", employee);
		request.setAttribute("page", new RdPage(employee).getPageStr("getEmployeePage"));
		return PathConstant.EmployeeList;
	}
	@RequestMapping("/delete")
	@RequiresPermissions("org:employee:delete")
	public void delete(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		int n=employeeservice.dimissionEmployee(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(n>=1) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "离职成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "离职失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping("/findEmpById")
	public void findEmployeeById(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		EmployeeInfoModel employee=employeemapper.findEmployeeExtInfoById(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(employee!=null) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			res.put("employee", employee);
			res.put("indate", sdf.format(employee.getEmpDate()!=null?employee.getEmpDate():new Date()));
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping("/getemployeetree")
	public void getEmployeeTree(HttpServletResponse response, HttpServletRequest request) {
		String emptreejson=SysUserAction.createEmployeeTree(deptmapper.listSelective(null), employeeservice.getEmployeeTree(null));
		Map<String, Object> res = new HashMap<String, Object>();
		if(emptreejson!=null) {
			res.put("emptree", emptreejson);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping("/getUnPostEmployeeTree")
	public void getUnPostEmployeeTree(HttpServletResponse response, HttpServletRequest request) {
		HashMap<String,	Object> param=new HashMap<String,Object>();
		param.put("parentId", 0);
		String emptreejson=SysUserAction.createUnPostEmployeeTree(deptmapper.listSelective(param), employeeservice.getEmployeeTree(null));
		Map<String, Object> res = new HashMap<String, Object>();
		if(emptreejson!=null) {
			res.put("emptree", emptreejson);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping("/getEmployeeBySession")
	public void getEmployeeBySession(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		SysUserModel user=(SysUserModel) request.getSession().getAttribute("SysUser");
		ServletUtils.writeToResponse(response, user);
	}
}
