package com.stone.equipsys.manage.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.RdPage;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.EquipPartsInfo;
import com.stone.equipsys.core.mapper.EquipPartsInfoMapper;
import com.stone.equipsys.core.model.EquipPartsInfoModel;
import com.stone.equipsys.core.service.EquipPartsInfoService;

@Controller
public class EquipPartsController {

	@Resource
	private EquipPartsInfoService PartsInfoService;
	@Resource
	private EquipPartsInfoMapper PartsInfoMapper;
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
	
	@ResponseBody
	@RequestMapping(value="equipparts/insert")
	public void insert(HttpServletResponse response, HttpServletRequest request,@RequestParam(value = "parts")String parts) {
		List<EquipPartsInfo> partslist=JSONArray.parseArray(parts,EquipPartsInfo.class);
		HashMap<String, Object> res=new HashMap<>();
		try {
			for(EquipPartsInfo part:partslist) {
				if(part!=null) PartsInfoMapper.save(part);
			}
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
