package com.stone.equipsys.core.service;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipServicingImplement;

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
}
