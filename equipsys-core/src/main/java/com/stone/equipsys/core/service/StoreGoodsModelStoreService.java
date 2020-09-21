package com.stone.equipsys.core.service;

import java.util.List;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.StoreGoodsModelStore;

/**
 * 物料库存映射表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-21 23:21:54
 */
public interface StoreGoodsModelStoreService extends BaseService<StoreGoodsModelStore, Long>{

	boolean saveModelStore(List<Long> stores,Long modelid);
}
