package com.stone.equipsys.core.mapper;

import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EquipServicingImplementOp;
import com.stone.equipsys.core.model.EquipServicingImplementOpModel;

/**
 * 设备维修人员记录表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 11:27:40
 */
@RDBatisDao
public interface EquipServicingImplementOpMapper extends BaseMapper<EquipServicingImplementOp, Long> {

    List<EquipServicingImplementOpModel> getOpByImplementid(Long implementId);

    void deleteOpByImplementId(Long implementId);
}
