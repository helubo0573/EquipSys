package com.stone.equipsys.core.service;

import java.util.HashMap;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipServicingApplication;
import com.stone.equipsys.core.model.EquipServicingApplicationModel;

/**
 * 设备维修申请Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-11 10:24:51
 */
public interface EquipServicingApplicationService extends BaseService<EquipServicingApplication, Long>{

	Page<EquipServicingApplicationModel> searchApplicationExtList(HashMap<String, Object> param,int currentPage, int pageSize);
}
