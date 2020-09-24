package com.stone.equipsys.core.mapper;

import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EquipOp;
import com.stone.equipsys.core.model.EquipOpModel;

/**
 * 	设备基础信息表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-04 10:24:08
 */
@RDBatisDao
public interface EquipOpMapper extends BaseMapper<EquipOp, Long> {

	void deleteOpByEquipId(int equipId);

	List<EquipOpModel> getOpByEquipid(int equipId);
    
	int countByEquipid(Long equipId);
}
