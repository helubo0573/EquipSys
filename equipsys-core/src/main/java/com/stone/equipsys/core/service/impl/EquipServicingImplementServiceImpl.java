package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipServicingImplementMapper;
import com.stone.equipsys.core.domain.EquipServicingImplement;
import com.stone.equipsys.core.service.EquipServicingImplementService;


/**
 * 设备维修信息管理表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 10:20:39
 */
 
@Service("equipServicingImplementService")
public class EquipServicingImplementServiceImpl extends BaseServiceImpl<EquipServicingImplement, Long> implements EquipServicingImplementService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipServicingImplementServiceImpl.class);
   
    @Resource
    private EquipServicingImplementMapper equipServicingImplementMapper;

	@Override
	public BaseMapper<EquipServicingImplement, Long> getMapper() {
		return equipServicingImplementMapper;
	}
	
}