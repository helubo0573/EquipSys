package com.stone.equipsys.core.service;

import java.util.List;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipServicingImplementParts;

/**
 * 设备维修零件耗用表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-11-17 09:03:42
 */
public interface EquipServicingImplementPartsService extends BaseService<EquipServicingImplementParts, Long>{

	void insertPartList(List<EquipServicingImplementParts> list);
	
	void deleteServicingImplementParts(Long implementid);
}
