package com.stone.equipsys.core.service;

import java.util.HashMap;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.StoreGoodsModelNumberInfo;

/**
 * 	物料规格表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-04 23:08:59
 */
public interface StoreGoodsModelNumberInfoService extends BaseService<StoreGoodsModelNumberInfo, Long>{

	Page<StoreGoodsModelNumberInfo> searchForPage(HashMap<String,Object> params,int currentPage,int pageSize);
	
	public void updateQuantity(Long modelid,int Quantity);
	
	void deleteModelNumber(Long id);
}
