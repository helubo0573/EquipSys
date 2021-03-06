package com.stone.equipsys.core.service;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipServicingImplementOp;

/**
 * 设备维修人员记录表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 11:27:40
 */
public interface EquipServicingImplementOpService extends BaseService<EquipServicingImplementOp, Long>{

	public HashMap<String, String> getOpByImplementId(Long id);
	
	boolean saveOpToList(List<Integer> oplist,Long id);
}
