package com.stone.equipsys.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.common.util.StringUtil;
import com.stone.equipsys.core.mapper.EquipServicingApplicationMapper;
import com.stone.equipsys.core.mapper.EquipServicingImplementMapper;
import com.stone.equipsys.core.mapper.EquipServicingImplementPartsMapper;
import com.stone.equipsys.core.service.EquipServicingImplementOpService;
import com.stone.equipsys.core.service.EquipServicingImplementPartsService;
import com.stone.equipsys.core.model.EquipServicingImplementModel;
import com.stone.equipsys.core.domain.EquipServicingApplication;
import com.stone.equipsys.core.domain.EquipServicingImplement;
import com.stone.equipsys.core.domain.EquipServicingImplementParts;
import com.stone.equipsys.core.service.EquipServicingImplementService;
import com.stone.equipsys.core.mapper.EquipServicingImplementOpMapper;


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
	private EquipServicingImplementPartsService EquipServicingImplementPartsService; 
	
	@Resource
	private EquipServicingImplementOpService EquipServicingImplementOpService;
	
	@Resource
	private EquipServicingImplementOpMapper EquipServicingImplementOpMapper;
	@Override
	public BaseMapper<EquipServicingImplement, Long> getMapper() {
		return equipServicingImplementMapper;
	}

	@Override
	@Transactional
	public boolean newServicingImplementService(Long proposer, int deptid, Long equipid, String Transactorid,
			Date application_time, Date backfire_time, Date SvrStartTime, Date SvrEndTime, String failureBewrite,
			String failureCause, String servicingCause,String parts) {
		try {
			EquipServicingApplication application=new EquipServicingApplication(0L,equipid,proposer,application_time,backfire_time,failureBewrite);
			ServicingApplicationmapper.insertReturnId(application);
			EquipServicingImplement ServicingImplement=new EquipServicingImplement(0L,proposer,equipid,application.getId(),deptid,SvrStartTime,SvrEndTime,failureBewrite,failureCause,servicingCause,0,0,0,0);
			equipServicingImplementMapper.save(ServicingImplement);
			List<EquipServicingImplementParts> partslist=JSONArray.parseArray(parts,EquipServicingImplementParts.class);
			if(partslist.size()>0) {
				for(EquipServicingImplementParts part:partslist) {
					part.setImplementId(ServicingImplement.getId());
					part.setEquipId(equipid);
				}
				EquipServicingImplementPartsService.insertPartList(partslist);
			}
			EquipServicingImplementOpService.saveOpToList(Transactorid.length()>0?StringUtil.convertStringToIntegerList(Transactorid, ","):null, ServicingImplement.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateServicingImplementService(Long id,Long appid,Long proposer, int deptid, Long equipid,
			String Transactorid, Date application_time, Date backfire_time, Date SvrStartTime, Date SvrEndTime,
			String failureBewrite, String failureCause, String servicingCause, String parts) {
		try {
			EquipServicingApplication application=new EquipServicingApplication(appid,equipid,proposer,application_time,backfire_time,failureBewrite);
			ServicingApplicationmapper.update(application);
			EquipServicingImplement ServicingImplement=new EquipServicingImplement(id,proposer,equipid,application.getId(),deptid,SvrStartTime,SvrEndTime,failureBewrite,failureCause,servicingCause,0,0,0,0);
			equipServicingImplementMapper.update(ServicingImplement);
			List<EquipServicingImplementParts> partslist=JSONArray.parseArray(parts,EquipServicingImplementParts.class);
			if(partslist.size()>0) {
				for(EquipServicingImplementParts part:partslist) {
					part.setImplementId(ServicingImplement.getId());
					part.setEquipId(equipid);
				}
				EquipServicingImplementPartsService.insertPartList(partslist);
			}else {
				EquipServicingImplementPartsService.deleteServicingImplementParts(id);
			}
			EquipServicingImplementOpService.saveOpToList(Transactorid.length()>0?StringUtil.convertStringToIntegerList(Transactorid, ","):null, ServicingImplement.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Page<EquipServicingImplementModel> getModelList(HashMap<String, Object> param, int currentPage,
			int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<EquipServicingImplementModel> list=equipServicingImplementMapper.listExtSelective(param);
		return (Page<EquipServicingImplementModel>) list;
	}

	@Override
	@Transactional
	public void deleteImplement(Long id) {
		EquipServicingImplementPartsService.deleteServicingImplementParts(id);
		EquipServicingImplementOpMapper.deleteOpByImplementId(id);
		equipServicingImplementMapper.deleteById(id);
	}

}