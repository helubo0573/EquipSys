package com.stone.equipsys.core.mapper;

import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.StoreGoodsModelStore;
import com.stone.equipsys.core.model.StoreGoodsModelStoreModel;

/**
 * 物料库存映射表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-21 23:21:54
 */
@RDBatisDao
public interface StoreGoodsModelStoreMapper extends BaseMapper<StoreGoodsModelStore, Long> {

	void deleteByModelid(Long modelid);
	
	List<StoreGoodsModelStoreModel> listSelectiveExt(Long id);

}
