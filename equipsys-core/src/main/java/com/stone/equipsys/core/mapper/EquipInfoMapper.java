package com.stone.equipsys.core.mapper;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EquipInfo;
import com.stone.equipsys.core.model.EquipInfoModel;

/**
 * 设备基础信息表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-04 10:08:34
 */
@RDBatisDao
public interface EquipInfoMapper extends BaseMapper<EquipInfo, Long> {

	/**
	 * 	增加后返回id
	 * @param equip
	 * @return  自增id
	 */
	int insertReturnId(EquipInfo equip);

	EquipInfoModel getEquipExtInfoById(Long id);

	boolean deleteEquipById(Long eid);
    

}
