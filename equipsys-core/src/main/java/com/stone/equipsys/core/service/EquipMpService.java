package com.stone.equipsys.core.service;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipMp;

/**
 * 	设备基础信息表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-07 12:19:20
 */
public interface EquipMpService extends BaseService<EquipMp, Long>{

	void deleteMpByEquipId(int id);
	
	/**
	 * @author Administrator	直接将员工id列表保存到维修员表中
	 * @param mplist	员工id列表
	 * @param equipid	设备id
	 * @return
	 */
	boolean saveMpToList(List<Integer> mplist,int equipid);

	HashMap<String, String> getMpByEquipid(int id);
}
