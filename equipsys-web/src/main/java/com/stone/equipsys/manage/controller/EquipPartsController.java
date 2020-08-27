package com.stone.equipsys.manage.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.RdPage;
import com.stone.equipsys.core.model.EquipPartsInfoModel;
import com.stone.equipsys.core.service.EquipPartsInfoService;

@Controller
public class EquipPartsController {

	@Resource
	private EquipPartsInfoService PartsInfoService;
	
	@RequestMapping("equipparts/search")
	public String search(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value ="equipid")Long equipid,
			@RequestParam(value="pageSize")int pageSize,
			@RequestParam(value="current")int current) {
			HashMap<String, Object> params=new HashMap<>();
			params.put("equipId", equipid);
			Page<EquipPartsInfoModel> partslist=PartsInfoService.searchExtEquipParts(params, current, pageSize);
			request.setAttribute("partslist", partslist);
			request.setAttribute("page", new RdPage(partslist).getPageStr("getPartslist"));
		return PathConstant.EquipPartsList;
		
	}
	
	@RequestMapping(value="equipparts/insert")
	public void insert(HttpServletResponse response, HttpServletRequest request) {
		
	}
}
