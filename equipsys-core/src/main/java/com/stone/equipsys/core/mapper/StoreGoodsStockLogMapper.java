package com.stone.equipsys.core.mapper;

import java.util.HashMap;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.StoreGoodsStockLog;

/**
 * 	物料明细存储纪录Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-07 23:00:22
 */
@RDBatisDao
public interface StoreGoodsStockLogMapper extends BaseMapper<StoreGoodsStockLog, Long> {

    int getcount(HashMap<String, Object> param);

}
