package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.Tree;
import com.stone.equipsys.core.domain.StoreGoodsType;
import com.stone.equipsys.core.mapper.StoreGoodsTypeMapper;

@Controller
public class StoreGoodsTypeController {

	@Resource
	private StoreGoodsTypeMapper GoodsTypeMapper;
	
	@RequestMapping("storegoods/manage")
	public String toManagePage(HttpServletResponse response, HttpServletRequest request) {
		List<StoreGoodsType> goodstypelist=GoodsTypeMapper.listSelective(null);
		request.setAttribute("typelist", goodstypelist);
		return PathConstant.GoodsType;
	}
	
	@RequestMapping("goodstype/save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0")Long id,
			@RequestParam(value="name")String typename,
			@RequestParam(value="order",defaultValue="1")int typeorder,
			@RequestParam(value="quickcode")String quickcode,
			@RequestParam(value="remarks")String remarks) {
		StoreGoodsType goodstype=new StoreGoodsType(id,typename,quickcode,typeorder,remarks);
		int n=0;
		if(id==0) {
			n=GoodsTypeMapper.save(goodstype);
		}else {
			n=GoodsTypeMapper.update(goodstype);
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
	
	@RequestMapping("goodstype/search")
	public String search(HttpServletResponse response, HttpServletRequest request) {
		List<StoreGoodsType> goodstypelist=GoodsTypeMapper.listSelective(null);
		request.setAttribute("typelist", goodstypelist);
		return PathConstant.GoodsTypeList;
	}
	
	@RequestMapping("goodstype/searchfortree")
	public void searchForTree(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		List<StoreGoodsType> goodstypelist=GoodsTypeMapper.listSelective(null);
		String restring=Tree.TreeJson(goodstypelist, "id", "typeName", "", "remarks");
		ServletUtils.writeToResponse(response, restring);
	}
}
