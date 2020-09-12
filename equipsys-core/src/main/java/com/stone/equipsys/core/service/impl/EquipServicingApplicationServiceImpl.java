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
import com.stone.equipsys.core.mapper.EquipServicingApplicationMapper;
import com.stone.equipsys.core.model.EquipServicingApplicationModel;
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

	@Override
	public Page<EquipServicingApplicationModel> searchApplicationExtList(HashMap<String, Object> param,int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<EquipServicingApplicationModel> list=equipServicingApplicationMapper.listExtSelective(param);
		return (Page<EquipServicingApplicationModel>) list;
	}
	
}