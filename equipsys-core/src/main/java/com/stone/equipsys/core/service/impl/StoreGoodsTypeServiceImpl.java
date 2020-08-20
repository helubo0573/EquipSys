package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.StoreGoodsTypeMapper;
import com.stone.equipsys.core.domain.StoreGoodsType;
import com.stone.equipsys.core.service.StoreGoodsTypeService;


/**
 * 库存物料类型表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-07-09 09:27:04
 */
 
@Service("storeGoodsTypeService")
public class StoreGoodsTypeServiceImpl extends BaseServiceImpl<StoreGoodsType, Long> implements StoreGoodsTypeService {
	
    private static final Logger logger = LoggerFactory.getLogger(StoreGoodsTypeServiceImpl.class);
   
    @Resource
    private StoreGoodsTypeMapper storeGoodsTypeMapper;

	@Override
	public BaseMapper<StoreGoodsType, Long> getMapper() {
		return storeGoodsTypeMapper;
	}
	
}