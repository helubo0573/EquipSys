package com.stone.equipsys.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.StoreGoodsModelStoreMapper;
import com.stone.equipsys.core.domain.StoreGoodsModelStore;
import com.stone.equipsys.core.service.StoreGoodsModelStoreService;


/**
 * 物料库存映射表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-21 23:21:54
 */
 
@Service("storeGoodsModelStoreService")
public class StoreGoodsModelStoreServiceImpl extends BaseServiceImpl<StoreGoodsModelStore, Long> implements StoreGoodsModelStoreService {
	
    private static final Logger logger = LoggerFactory.getLogger(StoreGoodsModelStoreServiceImpl.class);
   
    @Resource
    private StoreGoodsModelStoreMapper storeGoodsModelStoreMapper;

	@Override
	public BaseMapper<StoreGoodsModelStore, Long> getMapper() {
		return storeGoodsModelStoreMapper;
	}

	@Override
	public boolean saveModelStore(List<Long> storelist, Long modelid) {
		try {
			storeGoodsModelStoreMapper.deleteByModelid(modelid);
			if(storelist!=null) {
				for(Long storeid:storelist) {
					storeGoodsModelStoreMapper.save(new StoreGoodsModelStore(modelid,storeid));
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}