package com.stone.equipsys.core.service;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EquipOp;

/**
 * 	设备基础信息表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-04 10:24:08
 */
public interface EquipOpService extends BaseService<EquipOp, Long>{

	/**
	 * @author Administrator	直接将员工id列表保存到操作员表	
	 * @param oplist	id列表
	 * @param equipid	设备id
	 * @return
	 */
	boolean saveOpToList(List<Integer> oplist,int equipid);

	HashMap<String, String> getOpByEquipid(int id);
	
	void deleteOpByEquipId(int id);
}
