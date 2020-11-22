package com.stone.equipsys.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EquipServicingApplicationMapper;
import com.stone.equipsys.core.mapper.EquipServicingImplementMapper;
import com.stone.equipsys.core.mapper.EquipServicingImplementPartsMapper;
import com.stone.equipsys.core.domain.EquipServicingApplication;
import com.stone.equipsys.core.domain.EquipServicingImplement;
import com.stone.equipsys.core.domain.EquipServicingImplementParts;
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

    @Resource
	private EquipServicingApplicationMapper ServicingApplicationmapper;
	
	@Resource
	private EquipServicingImplementPartsMapper EquipServicingImplementPartsmapper; 
	
	@Override
	public BaseMapper<EquipServicingImplement, Long> getMapper() {
		return equipServicingImplementMapper;
	}

	@Override
	public Long insertRetrunId(EquipServicingImplement service) {
		return equipServicingImplementMapper.insertReturnId(service);
	}

	@Override
	public Long updateRetrun(EquipServicingImplement service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean newServicingImplementService(Long proposer, int deptid, Long equipid, String Transactorid,
			Date application_time, Date backfire_time, Date SvrStartTime, Date SvrEndTime, String failureBewrite,
			String failureCause, String servicingCause,String parts) {
		EquipServicingApplication application=new EquipServicingApplication(0L,equipid,proposer,application_time,backfire_time,failureBewrite);
		Long appid=ServicingApplicationmapper.insertReturnId(application);
		EquipServicingImplement ServicingImplement=new EquipServicingImplement(0L,proposer,equipid,appid,deptid,SvrStartTime,SvrEndTime,failureBewrite,failureCause,servicingCause,0,0,0,0);
		Long servicieid=insertRetrunId(ServicingImplement);
		List<EquipServicingImplementParts> partslist=JSONArray.parseArray(parts,EquipServicingImplementParts.class);
		for(EquipServicingImplementParts part:partslist) {
			part.setImplementId(servicieid);
			part.setEquipId(equipid);
			EquipServicingImplementPartsmapper.save(part);
		}
		return false;
	}
	
}