package com.stone.equipsys.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stone.equipsys.core.common.constant.PathConstant;


@Scope("prototype")
@Controller
public class MainPageController {
	
	@RequestMapping(value="/manage/mainpage")
	public String toMainPage(HttpServletRequest request,HttpServletResponse response) {
		return PathConstant.FrameMainPage;
	}
	
	@RequestMapping(value="/404")
	public String toErrorPage(HttpServletRequest request,HttpServletResponse response) {
		return PathConstant.ErrorPage;
	}
}
