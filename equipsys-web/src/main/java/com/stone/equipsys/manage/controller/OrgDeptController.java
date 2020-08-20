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

import com.alibaba.fastjson.JSONObject;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.Tree;
import com.stone.equipsys.core.domain.OrgDept;
import com.stone.equipsys.core.mapper.OrgDeptMapper;
import com.stone.equipsys.core.service.OrgDeptService;
import com.stone.equipsys.manage.TreeUtil.DeptUtil;
import com.stone.equipsys.manage.TreeUtil.DeptUtil.DeptObject;

@Controller
@RequiresPermissions("sys:admin")
public class OrgDeptController{

	@Resource
	private OrgDeptService DeptService;
	@Resource
	private OrgDeptMapper deptmapper;
	@RequestMapping(value="dept/manage")
	public String gotoManagePage(HttpServletResponse response, HttpServletRequest request) {
		
		return PathConstant.DeptManage;
	}
	@RequestMapping(value="dept/savedept")
	@RequiresPermissions("org:dept:save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="id")String id,
			@RequestParam(value="type")String type,
			@RequestParam(value="deptname")String deptname,
			@RequestParam(value="parentid")String parentid,
			@RequestParam(value="remarks")String remarks) {
		int n=0;
		OrgDept dept=new OrgDept(id,type,deptname,parentid,remarks);
		if("".equals(id)) {
			n=DeptService.insert(dept);
		}else {
			n=DeptService.updateById(dept);
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
	@RequestMapping(value="dept/getdepttree")
	public void search(HttpServletResponse response, HttpServletRequest request)
			throws UnsupportedEncodingException, IOException {
		List<DeptObject> deptlist=DeptUtil.createDeptTree(deptmapper.listSelective(null));
		String depttree=JSONObject.toJSONString(DeptUtil.setTreeIcon(deptlist));
		Map<String, Object> res = new HashMap<String, Object>();
		if(depttree!=null) {
			res.put("depttree", depttree);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping(value="dept/getparentdepttree")
	public void searchParentDept(HttpServletResponse response) {
		List<DeptObject> deptlist=DeptUtil.createDeptTree(deptmapper.findByDept());
		String depttree=JSONObject.toJSONString(DeptUtil.setTreeIcon(deptlist));
		Map<String, Object> res = new HashMap<String, Object>();
		if(depttree!=null) {
			res.put("parentdepttree", depttree);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping(value="dept/deletedept")
	@RequiresPermissions("org:dept:delete")
	public void delete(HttpServletResponse response, HttpServletRequest request) {
		String id=request.getParameter("id");
		int n=DeptService.deleteDept(id);
		Map<String, Object> res = new HashMap<String, Object>();
		if(n>0) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}

	@RequestMapping("dept/getdeptlist")
	public void getDeptList(HttpServletResponse response) {
		List<OrgDept> deptlist=deptmapper.listSelective(null);
		Map<String, Object> res = new HashMap<String, Object>();
		if(deptlist!=null) {
			res.put("deptlist", deptlist);
			res.put(Constant.RESPONSE_CODE_MSG, "查询成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
