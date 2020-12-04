package com.stone.equipsys.manage.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.RdPage;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.EquipServicingImplement;
import com.stone.equipsys.core.domain.EquipServicingImplementParts;
import com.stone.equipsys.core.mapper.EquipServicingImplementMapper;
import com.stone.equipsys.core.mapper.EquipServicingImplementOpMapper;
import com.stone.equipsys.core.mapper.EquipServicingImplementPartsMapper;
import com.stone.equipsys.core.model.EquipServicingApplicationModel;
import com.stone.equipsys.core.model.EquipServicingImplementDetailModel;
import com.stone.equipsys.core.model.EquipServicingImplementModel;
import com.stone.equipsys.core.service.EquipServicingImplementOpService;
import com.stone.equipsys.core.service.EquipServicingImplementService;
@Controller
@RequestMapping("EquipServicingImplement")
public class EquipServicingImplementController {

	@Resource
	private EquipServicingImplementService EquipServicingImplementsvc;
	@Resource
	private EquipServicingImplementMapper EquipServicingImplementmapper;
	@Resource
	private EquipServicingImplementOpService EquipServicingImplementOpService; 
	@Resource
	private EquipServicingImplementPartsMapper EquipServicingImplementPartsMapper;
	@RequestMapping("/Manage")
	public String toPage(HttpServletResponse response, HttpServletRequest request) {
		Page<EquipServicingImplementModel> list=EquipServicingImplementsvc.getModelList(null, 1, 12);
		request.setAttribute("ServicingImplementList", list);
		request.setAttribute("page", new RdPage(list).getPageStr("getEquipServicingImplementList"));
		return PathConstant.EquipServicingImplementManage;
	}
	
	@RequestMapping("/search")
	public String search(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "equipname")String equipname,
			@RequestParam(value="sappdate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date sappdate,
			@RequestParam(value="eappdate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date eappdate,
			@RequestParam(value="sbackfiredate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date sbackfiredate,
			@RequestParam(value = "ebackfiredate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date ebackfiredate,
			@RequestParam(value = "pageSize")int pageSize,
			@RequestParam(value = "current")int current) {
		HashMap<String, Object> param=new HashMap<String, Object>();
		param.put("equipname", equipname);
		param.put("sappdate", sappdate);
		param.put("eappdate", eappdate);
		param.put("sbackfiredate",sbackfiredate);
		param.put("ebackfiredate", ebackfiredate);
		Page<EquipServicingImplementModel> list=EquipServicingImplementsvc.getModelList(null, pageSize, current);
		request.setAttribute("ServicingImplementList", list);
		request.setAttribute("page", new RdPage(list).getPageStr("getEquipServicingImplementList"));
		return PathConstant.EquipServicingImplementList;
	}
	
	@RequestMapping("/getDetailInfo")
	public void getDetailInfo(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "id",defaultValue = "0")Long id) {
		EquipServicingImplementDetailModel Implement=EquipServicingImplementmapper.getDetailInfoByPrimary(id);
		HashMap<String, Object> PartsParam=new HashMap<String, Object>();
		PartsParam.put("implementId", id);
		List<EquipServicingImplementParts> parts=EquipServicingImplementPartsMapper.listSelective(PartsParam);
		HashMap<String, String> opmap=EquipServicingImplementOpService.getOpByImplementId(id);
		HashMap<String, Object> res=new HashMap<String, Object>();
		if(Implement!=null) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put("Implement", Implement);
			res.put("parts", parts);
			res.put("opmap", opmap);
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "查询失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	@ResponseBody
	@RequestMapping("/save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "id",defaultValue = "0")Long id,
			@RequestParam(value = "proposer")Long proposer,
			@RequestParam(value = "deptid")int deptid,
			@RequestParam(value="equipid")Long equipid,
			@RequestParam(value="Transactorid")String Transactorid,
			@RequestParam(value = "application_time") @DateTimeFormat(pattern= "yyyy-MM-dd") Date application_time,
			@RequestParam(value = "backfire_time") @DateTimeFormat(pattern= "yyyy-MM-dd") Date backfire_time,
			@RequestParam(value="SvrStartTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date SvrStartTime,
			@RequestParam(value="SvrEndTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date SvrEndTime,
			@RequestParam(value="failureBewrite")String failureBewrite,
			@RequestParam(value = "failureCause")String failureCause,
			@RequestParam(value = "servicingCause")String servicingCause,
			@RequestParam(value = "parts")String parts) {
		boolean flag=EquipServicingImplementsvc.newServicingImplementService(proposer, deptid, equipid, Transactorid, application_time, backfire_time, SvrStartTime, SvrEndTime, failureBewrite, failureCause, servicingCause, parts);
		HashMap<String, Object> res=new HashMap<String, Object>();
		if(flag) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, HttpServletRequest request,@RequestParam(value = "id")Long id) {
		HashMap<String, Object> res=new HashMap<String, Object>();
		try {
			EquipServicingImplementsvc.deleteImplement(id);
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
