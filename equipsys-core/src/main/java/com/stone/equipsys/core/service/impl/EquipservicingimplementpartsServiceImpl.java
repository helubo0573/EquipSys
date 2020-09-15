package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipservicingimplementpartsMapper;
import com.stone.equipsys.core.domain.Equipservicingimplementparts;
import com.stone.equipsys.core.service.EquipservicingimplementpartsService;


/**
 * 设备维修零件耗用表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 11:28:19
 */
 
@Service("equipservicingimplementpartsService")
public class EquipservicingimplementpartsServiceImpl extends BaseServiceImpl<Equipservicingimplementparts, Long> implements EquipservicingimplementpartsService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipservicingimplementpartsServiceImpl.class);
   
    @Resource
    private EquipservicingimplementpartsMapper equipservicingimplementpartsMapper;

	@Override
	public BaseMapper<Equipservicingimplementparts, Long> getMapper() {
		return equipservicingimplementpartsMapper;
	}
	
}