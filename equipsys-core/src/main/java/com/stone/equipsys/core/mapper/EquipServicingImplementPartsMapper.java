package com.stone.equipsys.core.mapper;

import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EquipServicingImplementParts;
import com.stone.equipsys.core.model.EquipServicingImplementPartsModel;

/**
 * 设备维修零件耗用表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-11-17 09:03:42
 */
@RDBatisDao
public interface EquipServicingImplementPartsMapper extends BaseMapper<EquipServicingImplementParts, Long> {

    void deleteByImplementId(Long implementId);
    
    List<EquipServicingImplementPartsModel> ExtlistSelective(Long implementId);
}
