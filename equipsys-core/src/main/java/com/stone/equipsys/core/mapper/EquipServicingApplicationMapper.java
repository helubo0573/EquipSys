package com.stone.equipsys.core.mapper;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EquipServicingApplication;
import com.stone.equipsys.core.model.EquipServicingApplicationModel;

/**
 * 设备维修申请Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-11 10:24:51
 */
@RDBatisDao
public interface EquipServicingApplicationMapper extends BaseMapper<EquipServicingApplication, Long> {

	List<EquipServicingApplicationModel> listExtSelective(HashMap<String, Object> param);
    

}
