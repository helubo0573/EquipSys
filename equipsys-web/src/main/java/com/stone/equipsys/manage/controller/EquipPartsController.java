package com.stone.equipsys.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EquipPartsController {

	@RequestMapping(value="equipparts/search")
	public String search(HttpServletResponse response, HttpServletRequest request) {
		return null;
		
	}
	
	@RequestMapping(value="equipparts/insert")
	public void insert(HttpServletResponse response, HttpServletRequest request) {
		
	}
}
