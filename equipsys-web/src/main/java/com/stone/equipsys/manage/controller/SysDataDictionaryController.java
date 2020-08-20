package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.SysDataDictionary;
import com.stone.equipsys.core.mapper.SysDataDictionaryMapper;

@Controller
public class SysDataDictionaryController {
	@Resource
	private SysDataDictionaryMapper SysDataDictionaryMapper;
	
	@RequestMapping("dictionary/list")
	public void getData(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="code")String code) throws UnsupportedEncodingException, IOException {
		HashMap<String, Object> param=new HashMap<String,Object>();
		param.put("dataCode", code);
		List<SysDataDictionary> datalist=SysDataDictionaryMapper.listSelective(param);
		ServletUtils.writeToResponse(response, datalist);
	}
}
