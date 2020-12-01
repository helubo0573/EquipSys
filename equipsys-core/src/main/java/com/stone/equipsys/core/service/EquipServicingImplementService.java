package com.stone.equipsys.core.service;

import java.util.Date;
import java.util.HashMap;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipServicingImplement;
import com.stone.equipsys.core.model.EquipServicingImplementModel;

/**
 * 设备维修信息管理表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 10:20:39
 */
public interface EquipServicingImplementService extends BaseService<EquipServicingImplement, Long>{

	Long insertRetrunId(EquipServicingImplement service);
	
	Long updateRetrun(EquipServicingImplement service);
	
	boolean newServicingImplementService(Long proposer,int deptid,Long equipid,String Transactorid,Date application_time,
			Date backfire_time,Date SvrStartTime,Date SvrEndTime,String failureBewrite,String failureCause,String servicingCause,String parts);
	
	Page<EquipServicingImplementModel> getModelList(HashMap<String, Object> param,int currentPage, int pageSize);
	
	void deleteImplement(Long id);
}
