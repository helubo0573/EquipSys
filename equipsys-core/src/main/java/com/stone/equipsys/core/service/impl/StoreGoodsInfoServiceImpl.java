package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.StoreGoodsInfoMapper;
import com.stone.equipsys.core.domain.StoreGoodsInfo;
import com.stone.equipsys.core.service.StoreGoodsInfoService;


/**
 * 物料信息表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-07-27 12:17:18
 */
 
@Service("storeGoodsInfoService")
public class StoreGoodsInfoServiceImpl extends BaseServiceImpl<StoreGoodsInfo, Long> implements StoreGoodsInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(StoreGoodsInfoServiceImpl.class);
   
    @Resource
    private StoreGoodsInfoMapper storeGoodsInfoMapper;

	@Override
	public BaseMapper<StoreGoodsInfo, Long> getMapper() {
		return storeGoodsInfoMapper;
	}
	
}