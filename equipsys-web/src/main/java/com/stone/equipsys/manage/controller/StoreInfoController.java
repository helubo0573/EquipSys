package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Array;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.Tree;
import com.stone.equipsys.core.domain.StoreInfo;
import com.stone.equipsys.core.mapper.StoreInfoMapper;
import com.stone.equipsys.core.service.StoreInfoService;
import com.stone.equipsys.manage.TreeUtil.StoreUtil;

@Controller
public class StoreInfoController {

	@Resource
	private StoreInfoService StoreService;
	@Resource
	private StoreInfoMapper StoreMapper;
	@RequestMapping(value="storeinfo/manage")
	public String toManagePage(HttpServletResponse response, HttpServletRequest request) {
		
		return PathConstant.StoreInfo;
	}
	
	@RequestMapping(value="storeinfo/search")
	public void search(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="type",defaultValue="-1")int type) throws UnsupportedEncodingException, IOException {
		String StoreTree="";
		List<StoreInfo> storelist=new ArrayList<>();
		if(type==-1) {
			HashMap<String, Object> param=new HashMap<>();
			param.put("parentId", 0);
			storelist=StoreMapper.listSelective(param);
		}else {
			storelist=StoreMapper.listSelective(null);
		}
		StoreTree=JSONObject.toJSONString(StoreUtil.StoreTreeList(storelist, type==1?false:true));
		ServletUtils.writeToResponse(response, StoreTree);
	}
	@RequestMapping(value="storeinfo/save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="storeid",defaultValue="-1")Long id,
			@RequestParam(value="parentid",defaultValue="0")Long parentid,
			@RequestParam(value="storename")String storename,
			@RequestParam(value="storeorder")int order,
			@RequestParam(value="remarks",defaultValue="无")String remarks) {
		StoreInfo store=new StoreInfo(id,parentid,storename,order,remarks);
		int n=0;
		if(id==-1) {
			n=StoreMapper.save(store);
		}else {
			n=StoreMapper.update(store);
		}
		Map<String, Object> res = new HashMap<String, Object>();
		if(n>0) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "保存成功");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="storeinfo/delete")
	public void delete(HttpServletResponse response, HttpServletRequest request,@RequestParam(value="id")Long id) {
		//由于删除数据存在逻辑关系，延后开发
	}
}
