package com.stone.equipsys.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stone.equipsys.core.common.constant.PathConstant;

@Controller
@RequestMapping ("EquipServicingApplication")
public class EquipServicingApplicationController {

	@RequestMapping("/Manage")
	public String toPage(HttpServletResponse response, HttpServletRequest request) {
		return PathConstant.EquipServicingApplicationManage;
	}
}
