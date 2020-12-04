package com.stone.equipsys.core.mapper;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EquipServicingImplement;
import com.stone.equipsys.core.model.EquipServicingImplementDetailModel;
import com.stone.equipsys.core.model.EquipServicingImplementModel;

/**
 * 设备维修信息管理表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-15 10:20:39
 */
@RDBatisDao
public interface EquipServicingImplementMapper extends BaseMapper<EquipServicingImplement, Long> {

    Long insertReturnId(EquipServicingImplement service);

    List<EquipServicingImplementModel> listExtSelective(HashMap<String, Object> param);
    
    void deleteById(Long id);
    
    EquipServicingImplementDetailModel getDetailInfoByPrimary(Long id);
}
