package com.stone.equipsys.core.mapper;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EquipPartsInfo;
import com.stone.equipsys.core.model.EquipPartsInfoModel;

/**
 * 设备配件信息Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-25 12:14:22
 */
@RDBatisDao
public interface EquipPartsInfoMapper extends BaseMapper<EquipPartsInfo, Long> {

    List<EquipPartsInfoModel> listSelectiveExt(HashMap<String, Object> param);

}
