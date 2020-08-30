package com.stone.equipsys.core.mapper;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.StoreGoodsInventoryRecords;

/**
 * 	物料明细存储纪录Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-04 23:54:44
 */
@RDBatisDao
public interface StoreGoodsInventoryRecordsMapper extends BaseMapper<StoreGoodsInventoryRecords, Long> {

    public int countquantity(Long modelid);

}
