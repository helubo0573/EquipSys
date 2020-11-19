package com.stone.equipsys.manage.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stone.equipsys.core.common.constant.PathConstant;

@Controller
@RequestMapping("EquipServicingImplement")
public class EquipServicingImplementController {

	
	@RequestMapping("/Manage")
	public String toPage(HttpServletResponse response, HttpServletRequest request) {
		
		return PathConstant.EquipServicingImplementManage;
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
			@RequestParam(value="SvrStartTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:ss") Date SvrStartTime,
			@RequestParam(value="SvrEndTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:ss") Date SvrEndTime,
			@RequestParam(value="FaultSketch")String FaultSketch,
			@RequestParam(value = "FaultAnalyse")String FaultAnalyse,
			@RequestParam(value = "FaultResult")String FaultResult,
			@RequestParam(value = "parts")String parts) {
		
	}
}
