package com.stone.equipsys.core.service;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.StoreGoodsInventoryRecords;
import com.stone.equipsys.core.model.SysUserModel;

/**
 * 物料明细存储纪录Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-04 23:54:44
 */
public interface StoreGoodsInventoryRecordsService extends BaseService<StoreGoodsInventoryRecords, Long>{

	/**
	 * 	物料入库
	 * @param record	物料入库对象
	 * @param user		操作user对象
	 * @return
	 */
	boolean incomeRecord(StoreGoodsInventoryRecords record,SysUserModel user,int changetype);
}
