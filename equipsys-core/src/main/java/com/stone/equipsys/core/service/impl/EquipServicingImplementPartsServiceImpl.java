package com.stone.equipsys.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipServicingImplementPartsMapper;
import com.stone.equipsys.core.domain.EquipServicingImplementParts;
import com.stone.equipsys.core.service.EquipServicingImplementPartsService;


/**
 * 设备维修零件耗用表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-11-17 09:03:42
 */
 
@Service("equipServicingImplementPartsService")
public class EquipServicingImplementPartsServiceImpl extends BaseServiceImpl<EquipServicingImplementParts, Long> implements EquipServicingImplementPartsService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipServicingImplementPartsServiceImpl.class);
   
    @Resource
    private EquipServicingImplementPartsMapper equipServicingImplementPartsMapper;

	@Override
	public BaseMapper<EquipServicingImplementParts, Long> getMapper() {
		return equipServicingImplementPartsMapper;
	}

	@Override
	public void insertPartList(List<EquipServicingImplementParts> list) {
		deleteServicingImplementParts(list.get(0).getImplementId());
		for(EquipServicingImplementParts part:list) {
			equipServicingImplementPartsMapper.save(part);
		}
	}

	@Override
	public void deleteServicingImplementParts(Long implementid) {
		equipServicingImplementPartsMapper.deleteByImplementId(implementid);
	}
	
}