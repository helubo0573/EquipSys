package com.stone.equipsys.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.StoreGoodsInventoryRecordsMapper;
import com.stone.equipsys.core.mapper.StoreGoodsModelNumberInfoMapper;
import com.stone.equipsys.core.mapper.StoreGoodsStockLogMapper;
import com.stone.equipsys.core.domain.StoreGoodsInventoryRecords;
import com.stone.equipsys.core.domain.StoreGoodsModelNumberInfo;
import com.stone.equipsys.core.service.StoreGoodsModelNumberInfoService;


/**
 * 物料规格表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-04 23:08:59
 */
 
@Service("storeGoodsModelNumberInfoService")
public class StoreGoodsModelNumberInfoServiceImpl extends BaseServiceImpl<StoreGoodsModelNumberInfo, Long> implements StoreGoodsModelNumberInfoService {
	
	@Resource
	private StoreGoodsModelNumberInfoMapper ModelNumberMapper;
	@Resource
	private StoreGoodsInventoryRecordsMapper InventoryRecordsMapper;
	@Resource
	private StoreGoodsStockLogMapper StockLogMapper;
    private static final Logger logger = LoggerFactory.getLogger(StoreGoodsModelNumberInfoServiceImpl.class);
   
    @Resource
    private StoreGoodsModelNumberInfoMapper storeGoodsModelNumberInfoMapper;

	@Override
	public BaseMapper<StoreGoodsModelNumberInfo, Long> getMapper() {
		return storeGoodsModelNumberInfoMapper;
	}
	
	public boolean inserterModelNumber(StoreGoodsModelNumberInfo modelnumber,StoreGoodsInventoryRecords inventoryrecords) {
		int n=ModelNumberMapper.save(modelnumber);
		Long id=modelnumber.getId();
		if(modelnumber.getQuantity()>0) {
			inventoryrecords.setModelId(id);
			int i=InventoryRecordsMapper.save(inventoryrecords);
			if(i>0) {
				
			}
		}
		Date indate=new Date();
		
		
		return false;
		
		
	}

	@Override
	public Page<StoreGoodsModelNumberInfo> searchForPage(HashMap<String, Object> params, int currentPage,
			int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<StoreGoodsModelNumberInfo> modellist=storeGoodsModelNumberInfoMapper.listSelective(params);
		return (Page<StoreGoodsModelNumberInfo>) modellist;
	}

	@Override
	public void updateQuantity(Long modelid, int Quantity) {
		HashMap<String, Object> param=new HashMap<String,Object>();
		param.put("id", modelid);
		param.put("quantity", Quantity);
		ModelNumberMapper.updateSelective(param);
	}
	
	
}