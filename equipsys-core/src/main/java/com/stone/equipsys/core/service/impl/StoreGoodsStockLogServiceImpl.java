package com.stone.equipsys.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.StoreGoodsStockLogMapper;
import com.stone.equipsys.core.domain.StoreGoodsStockLog;
import com.stone.equipsys.core.service.StoreGoodsStockLogService;


/**
 * 物料明细存储纪录ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-07 23:00:22
 */
 
@Service("storeGoodsStockLogService")
public class StoreGoodsStockLogServiceImpl extends BaseServiceImpl<StoreGoodsStockLog, Long> implements StoreGoodsStockLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(StoreGoodsStockLogServiceImpl.class);
   
    @Resource
    private StoreGoodsStockLogMapper storeGoodsStockLogMapper;

	@Override
	public BaseMapper<StoreGoodsStockLog, Long> getMapper() {
		return storeGoodsStockLogMapper;
	}

	/**
	 * @author Administrator
	 * @param stocktype	0-20为入库  21-为入库 
	 */
	@Override
	public String createOrderCode(int stocktype) {
		HashMap<String, Object> param=new HashMap<String,Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat paramsdf=new SimpleDateFormat("yyyy-MM-dd");
		Date indate=new Date();
		param.put("changeTime", paramsdf.format(indate));
		param.put("changeType", 0);
		int count=storeGoodsStockLogMapper.listSelective(param).size();
		String typestr=stocktype<=20?"I":"O";
		return typestr+sdf.format(indate)+String.format("%03d", count);
	}

	@Override
	public boolean insertLog(Long modelnumberid, int type, int quantity,  int overplus,String op,String remarks) {
		Date indate=new Date();
		StoreGoodsStockLog log=new StoreGoodsStockLog(createOrderCode(type),modelnumberid,type,indate,quantity,overplus,op,remarks);
		int flag=storeGoodsStockLogMapper.save(log);
		return flag==1?true:false;
	}
	
	
}