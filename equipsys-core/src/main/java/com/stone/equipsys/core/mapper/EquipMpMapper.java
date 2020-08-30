package com.stone.equipsys.core.mapper;

import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EquipMp;
import com.stone.equipsys.core.model.EquipMpModel;

/**
 * 	设备基础信息表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-07 12:19:20
 */
@RDBatisDao
public interface EquipMpMapper extends BaseMapper<EquipMp, Long> {

	void deleteMpByEquipId(int equipId);

	List<EquipMpModel> getMpByEquipid(int equipId);

    

}
