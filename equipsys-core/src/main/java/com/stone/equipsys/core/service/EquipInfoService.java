package com.stone.equipsys.core.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipInfo;
import com.stone.equipsys.core.model.EquipInfoModel;

/**
 *	 设备基础信息表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-04 10:08:34
 */
public interface EquipInfoService extends BaseService<EquipInfo, Long>{
	
	int insertReturnId(EquipInfo equip) throws ParseException;

	int updateRetuenId(EquipInfo equip) throws ParseException;
	
	List<EquipInfo> getEquip(int type);
	
	EquipInfoModel getEquipExtInfoById(Long id);

	void deleteEquipById(Long eid);
}
