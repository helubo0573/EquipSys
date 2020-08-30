package com.stone.equipsys.core.service;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.StoreGoodsStockLog;

/**
 * 	物料明细存储纪录Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-07 23:00:22
 */
public interface StoreGoodsStockLogService extends BaseService<StoreGoodsStockLog, Long>{

	/**
	 * 生成入库编号
	 * @param stocktype 发生类型 false:出库   true:入库
	 * @return
	 */
	String createOrderCode(int stocktype);
	
	boolean insertIncomeLog(Long modelnumberid,int type,int quantity,float price,String supplier,int overplus,String op,String remarks);
	
	boolean insertOutcomeLog(Long modelnumberid,int type,int quantity,Long employeeid,Long deptid,String use,int overplus,String op,String remarks);
}
