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

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.constant.PathConstant;
import com.stone.equipsys.core.common.util.RdPage;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.StringUtil;
import com.stone.equipsys.core.domain.StoreGoodsModelNumberInfo;
import com.stone.equipsys.core.mapper.StoreGoodsModelNumberInfoMapper;
import com.stone.equipsys.core.mapper.StoreGoodsStockLogMapper;
import com.stone.equipsys.core.model.StoreGoodsModelStoreModel;
import com.stone.equipsys.core.service.StoreGoodsModelNumberInfoService;
import com.stone.equipsys.core.service.StoreGoodsModelStoreService;
import com.stone.equipsys.core.mapper.StoreGoodsModelStoreMapper;
@Controller
@RequestMapping("modelnumber")
public class StoreGoodsModelNumberController {
	
	@Resource
	private StoreGoodsStockLogMapper StoreGoodsStockLogMapper;
	@Resource
	private StoreGoodsModelNumberInfoMapper StoreGoodsModelNumberMapper;
	@Resource
	private StoreGoodsModelNumberInfoService StoreGoodsModelNumberInfoService;
	@Resource
	private StoreGoodsModelStoreService StoreGoodsModelStoreService;
	@Resource
	private StoreGoodsModelStoreMapper StoreGoodsModelStoreMapper;
	@RequestMapping("/save")
	@RequiresPermissions(value = "store:goodsmodelnumber:save")
	public void save(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0")Long id,
			@RequestParam(value="goodsid")Long goodsid,
			@RequestParam(value="modelname")String name,
			@RequestParam(value="unit",defaultValue="个")String unit,
			@RequestParam(value="remarks",defaultValue=" ")String remarks,
			@RequestParam(value = "storeid")String storeids) {
		StoreGoodsModelNumberInfo modelnumber=new StoreGoodsModelNumberInfo(id,goodsid,name,0,0.00F,unit,remarks);
		int n=0;
		if(id==0)
			n=StoreGoodsModelNumberMapper.save(modelnumber);
		else
			n=StoreGoodsModelNumberMapper.update(modelnumber);
		StoreGoodsModelStoreService.saveModelStore(storeids.length()>0?StringUtil.convertStringToLongList(storeids, ","):null, modelnumber.getId());
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
	@RequestMapping("/search")
	public String search(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="goodsid")Long goodsid,
			@RequestParam(value="pagenum",defaultValue="1")int currentPage,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize) {
			HashMap<String, Object> param=new HashMap<String,Object>();
			param.put("goodsId", goodsid);
			param.put("logicalState", false);
			Page<StoreGoodsModelNumberInfo> modellist=StoreGoodsModelNumberInfoService.searchForPage(param, currentPage, pageSize);
			request.setAttribute("modellist", modellist);
			request.setAttribute("page", new RdPage(modellist).getPageStr("getModelNumberList"));
		return PathConstant.ModelNumberList;
		
	}
	
	@RequestMapping("/getDetailInfo")
	public void getinfo(HttpServletResponse response, HttpServletRequest request,@RequestParam(value = "id")Long id) throws UnsupportedEncodingException, IOException {
		StoreGoodsModelNumberInfo goodsmodel=StoreGoodsModelNumberMapper.findByPrimary(id);
		List<StoreGoodsModelStoreModel> modelstorelist=StoreGoodsModelStoreMapper.listSelectiveExt(id);
		String storeid="";
		String storename="";
		HashMap<String, String> storemap=new HashMap<String,String>();
		for(StoreGoodsModelStoreModel modelstore:modelstorelist) {
			storeid+=modelstore.getStoreId()+",";
			storename+=modelstore.getStorename()+",";
		}
		storemap.put("storeids", storeid.length()>0?storeid.substring(0, storeid.length()-1):storeid);
		storemap.put("storenames", storename.length()>0?storename.substring(0, storename.length()-1):storename);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("model", goodsmodel);
		res.put("storelist", storemap);
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, HttpServletRequest request,@RequestParam(value = "id")Long id) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			StoreGoodsModelNumberInfoService.deleteModelNumber(id);
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "删除失败");
		}
		ServletUtils.writeToResponse(response, res);
	}
}
