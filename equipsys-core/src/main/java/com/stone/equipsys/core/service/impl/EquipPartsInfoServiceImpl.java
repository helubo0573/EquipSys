package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipPartsInfoMapper;
import com.stone.equipsys.core.model.EquipPartsInfoModel;
import com.stone.equipsys.core.domain.EquipPartsInfo;
import com.stone.equipsys.core.service.EquipPartsInfoService;


/**
 * 设备配件信息ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-25 12:14:22
 */
 
@Service("equipPartsInfoService")
public class EquipPartsInfoServiceImpl extends BaseServiceImpl<EquipPartsInfo, Long> implements EquipPartsInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(EquipPartsInfoServiceImpl.class);
   
    @Resource
    private EquipPartsInfoMapper equipPartsInfoMapper;

	@Override
	public BaseMapper<EquipPartsInfo, Long> getMapper() {
		return equipPartsInfoMapper;
	}

	@Override
	public Page<EquipPartsInfoModel> searchExtEquipParts(HashMap<String, Object> params, int currentPage,
			int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<EquipPartsInfoModel> partslist=equipPartsInfoMapper.listSelectiveExt(params);
		return (Page<EquipPartsInfoModel>) partslist;
	}
	
}