package com.stone.equipsys.core.service;

import java.util.HashMap;

import com.github.pagehelper.Page;
import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipPartsInfo;
import com.stone.equipsys.core.model.EquipPartsInfoModel;

/**
 * 设备配件信息Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-25 12:14:22
 */
public interface EquipPartsInfoService extends BaseService<EquipPartsInfo, Long>{

	public Page<EquipPartsInfoModel> searchExtEquipParts(HashMap<String, Object> params, int currentPage, int pageSize);
}
