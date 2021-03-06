package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.RdPage;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.EquipServicingApplication;
import com.stone.equipsys.core.mapper.EquipServicingApplicationMapper;
import com.stone.equipsys.core.model.EquipServicingApplicationModel;
import com.stone.equipsys.core.model.SysUserModel;
import com.stone.equipsys.core.service.EquipServicingApplicationService;

@Controller
@RequestMapping ("EquipServicingApplication")
public class EquipServicingApplicationController {

	@Resource
	private EquipServicingApplicationMapper ServicingApplicationMapper;
	@Resource
	private EquipServicingApplicationService ServicingApplicationService;
	
	@RequestMapping("/Manage")
	public String toPage(HttpServletResponse response, HttpServletRequest request) {
		Page<EquipServicingApplicationModel> applicationList=ServicingApplicationService.searchApplicationExtList(null, 1, 12);
		request.setAttribute("applicationList", applicationList);
		request.setAttribute("page", new RdPage(applicationList).getPageStr("getEquipServicingApplicationList"));
		return PathConstant.EquipServicingApplicationManage;
	}
	
	@RequestMapping("/search")
	public String search(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="equipname",defaultValue = "")String equipname,
			@RequestParam(value="sappdate",defaultValue = "")String sappdate,
			@RequestParam(value="eappdate",defaultValue = "")String eappdate,
			@RequestParam(value="sbackfiredate",defaultValue = "")String sbackfiredate,
			@RequestParam(value="ebackfiredate",defaultValue = "")String ebackfiredate,
			@RequestParam(value="pageSize")int pageSize,
			@RequestParam(value="current")int current) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Object> param=new HashMap<String, Object>();
		if(equipname!="")	param.put("equipName", equipname);
		if(!"".equals(sappdate))	param.put("sappDate", sdf.parse(sappdate));
		if(!"".equals(eappdate))	param.put("eappDate", sdf.parse(eappdate));
		if(!"".equals(sbackfiredate))	param.put("sbackfireDate", sdf.parse(sbackfiredate));
		if(!"".equals(ebackfiredate))	param.put("ebackfireDate", sdf.parse(ebackfiredate));
		Page<EquipServicingApplicationModel> applicationList=ServicingApplicationService.searchApplicationExtList(param, current, pageSize);
		request.setAttribute("applicationList", applicationList);
		request.setAttribute("page", new RdPage(applicationList).getPageStr("getEquipServicingApplicationList"));
		return PathConstant.EquipServicingApplicationList;
	}
	@RequestMapping("/searchforjson")
	public void search(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="equipname",defaultValue = "")String equipname,
			@RequestParam(value="sappdate",defaultValue = "")String sappdate,
			@RequestParam(value="eappdate",defaultValue = "")String eappdate,
			@RequestParam(value="sbackfiredate",defaultValue = "")String sbackfiredate,
			@RequestParam(value="ebackfiredate",defaultValue = "")String ebackfiredate) throws ParseException, UnsupportedEncodingException, IOException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, Object> param=new HashMap<String, Object>();
		if(equipname!="")	param.put("equipName", equipname);
		if(!"".equals(sappdate))	param.put("sappDate", sdf.parse(sappdate));
		if(!"".equals(eappdate))	param.put("eappDate", sdf.parse(eappdate));
		if(!"".equals(sbackfiredate))	param.put("sbackfireDate", sdf.parse(sbackfiredate));
		if(!"".equals(ebackfiredate))	param.put("ebackfireDate", sdf.parse(ebackfiredate));
		List<EquipServicingApplicationModel> list=ServicingApplicationMapper.listExtSelective(param);
		HashMap<String, Object> res=new HashMap<String, Object>();
		res.put("data", list);
		ServletUtils.writeToResponse(response, res);
	}
	@RequestMapping("/save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="id",defaultValue = "0")Long id,
			@RequestParam(value="equipid")Long equipid,
			@RequestParam(value="application_time") @DateTimeFormat(pattern = "yyyy-MM-dd")Date application_time,
			@RequestParam(value="backfire_time") @DateTimeFormat(pattern = "yyyy-MM-dd")Date backfire_time,
			@RequestParam(value="remarks")String remarks) {
		SysUserModel user=(SysUserModel) request.getSession().getAttribute("SysUser");
		EquipServicingApplication application=new EquipServicingApplication(id,equipid,user.getId(),application_time,backfire_time,remarks);
		application.setStatus(0);
		int n=0;
		if(id==0) {
			n=ServicingApplicationMapper.save(application);
		}else {
			n=ServicingApplicationMapper.update(application);
		}
		HashMap<String, Object> res=new HashMap<String, Object>();
		if(n>0) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping("/getApplication")
	public void getApplicationById(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="id")Long id) throws UnsupportedEncodingException, IOException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		EquipServicingApplicationModel application=ServicingApplicationMapper.findExtByPrimary(id);
		HashMap<String, Object> res=new HashMap<String, Object>();
		res.put("application", application);
		res.put("applicationTime", sdf.format(application.getApplicationTime()));
		res.put("backfireTime", sdf.format(application.getBackfireTime()));
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "id")Long id) {
		HashMap<String, Object> res=new HashMap<String, Object>();
		try {
			ServicingApplicationMapper.deleteById(id);
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
