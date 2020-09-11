package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipServicingApplicationMapper;
import com.stone.equipsys.core.domain.EquipServicingApplication;
import com.stone.equipsys.core.service.EquipServicingApplicationService;


/**
 * 设备维修申请ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-11 10:24:51
 */
 
@Service("equipServicingApplicationService")
public class EquipServicingApplicationServiceImpl extends BaseServiceImpl<EquipServicingApplication, Long> implements EquipServicingApplicationService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipServicingApplicationServiceImpl.class);
   
    @Resource
    private EquipServicingApplicationMapper equipServicingApplicationMapper;

	@Override
	public BaseMapper<EquipServicingApplication, Long> getMapper() {
		return equipServicingApplicationMapper;
	}
	
}