package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.StoreInfoMapper;
import com.stone.equipsys.core.domain.StoreInfo;
import com.stone.equipsys.core.service.StoreInfoService;


/**
 * 	系统用户表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-07-09 08:52:56
 */
 
@Service("storeInfoService")
public class StoreInfoServiceImpl extends BaseServiceImpl<StoreInfo, Long> implements StoreInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(StoreInfoServiceImpl.class);
   
    @Resource
    private StoreInfoMapper storeInfoMapper;

	@Override
	public BaseMapper<StoreInfo, Long> getMapper() {
		return storeInfoMapper;
	}
	
}