package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.StoreGoodsInfo;
import com.stone.equipsys.core.mapper.EquipPartsInfoMapper;
import com.stone.equipsys.core.mapper.StoreGoodsInfoMapper;
import com.stone.equipsys.core.mapper.StoreGoodsModelNumberInfoMapper;
import com.stone.equipsys.core.mapper.StoreGoodsTypeMapper;
import com.stone.equipsys.core.service.StoreGoodsInfoService;
import com.stone.equipsys.manage.TreeUtil.StoreStockGoodsUtil;

@Controller
public class StoreStockGoodsInfoController {

	@Resource
	private StoreGoodsInfoService StoreGoodsInfoService;
	@Resource
	private StoreGoodsInfoMapper StoreGoodsInfoMapper;
	@Resource
	private StoreGoodsTypeMapper StoreGoodsTypeMapper; 
	@Resource
	private StoreGoodsModelNumberInfoMapper ModelNumberInfoMapper;
	@Resource
	private EquipPartsInfoMapper PartsInfoMapper;
	@RequestMapping(value="storestockgoodsinfo/manage")
	public String toManagePage(HttpServletResponse response, HttpServletRequest request) {
		
		return PathConstant.StoreStockGoodsInfo;
	}
	
	@RequestMapping(value="storestockgoodsinfo/search")
	public void	search(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException, IOException {
		String GoodsStockJson=JSONObject.toJSONString(StoreStockGoodsUtil.CreateStockGoodsList(StoreGoodsTypeMapper.listSelective(null), StoreGoodsInfoMapper.listSelective(null)));
		ServletUtils.writeToResponse(response, GoodsStockJson);
	}
	
	@RequestMapping(value="storestockgoodsinfo/save")
	@RequiresPermissions(value = "store:goodsinfo:save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="goodsid",defaultValue="0")Long id,
			@RequestParam(value="typeid")Long typeid,
			@RequestParam(value="goodsname")String goodsname,
			@RequestParam(value="remarks",defaultValue="")String remarks) {
		int n=0;
		if(id==0) {
			n=StoreGoodsInfoMapper.save(new StoreGoodsInfo(id,goodsname,typeid,remarks));
		}else {
			n=StoreGoodsInfoMapper.update(new StoreGoodsInfo(id,goodsname,typeid,remarks));
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
	
	@RequestMapping("storestockgoodsinfo/goodsdetailjson")
	public void getGoodsModelDetailInfoTree(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="equipid")Long equipid,
			@RequestParam(value="goodstype",defaultValue = "0")Long goodstype) throws UnsupportedEncodingException, IOException {
		HashMap<String, Object> param=new HashMap<>();
		HashMap<String,Object> goodstypeparam=new HashMap<>();
		param.put("equipId", equipid);
		if(goodstype!=0) goodstypeparam.put("id", goodstype); else goodstypeparam=null;
		String treejson=JSONObject.toJSONString(StoreStockGoodsUtil.CreateStockGoodsModelObjectList(
				StoreGoodsTypeMapper.listSelective(goodstypeparam), 
				StoreGoodsInfoMapper.listSelective(null), 
				ModelNumberInfoMapper.listSelective(null),
				PartsInfoMapper.listSelective(param)));
		ServletUtils.writeToResponse(response, treejson);
	}
}
