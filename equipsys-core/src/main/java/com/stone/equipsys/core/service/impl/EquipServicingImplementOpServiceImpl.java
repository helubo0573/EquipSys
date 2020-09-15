package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipServicingImplementOpMapper;
import com.stone.equipsys.core.domain.EquipServicingImplementOp;
import com.stone.equipsys.core.service.EquipServicingImplementOpService;


/**
 * 设备维修人员记录表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 11:27:40
 */
 
@Service("equipServicingImplementOpService")
public class EquipServicingImplementOpServiceImpl extends BaseServiceImpl<EquipServicingImplementOp, Long> implements EquipServicingImplementOpService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipServicingImplementOpServiceImpl.class);
   
    @Resource
    private EquipServicingImplementOpMapper equipServicingImplementOpMapper;

	@Override
	public BaseMapper<EquipServicingImplementOp, Long> getMapper() {
		return equipServicingImplementOpMapper;
	}
	
}