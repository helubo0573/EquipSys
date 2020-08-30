package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.StoreGoodsInventoryRecordsMapper;
import com.stone.equipsys.core.model.SysUserModel;
import com.stone.equipsys.core.domain.StoreGoodsInventoryRecords;
import com.stone.equipsys.core.service.StoreGoodsInventoryRecordsService;
import com.stone.equipsys.core.service.StoreGoodsModelNumberInfoService;
import com.stone.equipsys.core.service.StoreGoodsStockLogService;


/**
 * 	物料明细存储纪录ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-04 23:54:44
 */
 
@Service("storeGoodsInventoryRecordsService")
public class StoreGoodsInventoryRecordsServiceImpl extends BaseServiceImpl<StoreGoodsInventoryRecords, Long> implements StoreGoodsInventoryRecordsService {
	
    private static final Logger logger = LoggerFactory.getLogger(StoreGoodsInventoryRecordsServiceImpl.class);
    
    @Resource
    private StoreGoodsStockLogService StockLogService;
    @Resource
    private StoreGoodsModelNumberInfoService ModelNumberInfoService;
    @Resource
    private StoreGoodsInventoryRecordsMapper storeGoodsInventoryRecordsMapper;

	@Override
	public BaseMapper<StoreGoodsInventoryRecords, Long> getMapper() {
		return storeGoodsInventoryRecordsMapper;
	}

	@Override
	@Transactional
	public boolean incomeRecord(StoreGoodsInventoryRecords record, SysUserModel user,int changetype) {
		try {
			int flag=this.getMapper().save(record);
			if(flag>0) {
				int sum=storeGoodsInventoryRecordsMapper.countquantity(record.getModelId());
				ModelNumberInfoService.updateQuantity(record.getModelId(), sum);
				StockLogService.insertIncomeLog(record.getModelId(), changetype, record.getQuantity(),record.getPrice(),record.getSupplier(), sum, user.getEmpname(), record.getRemarks());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean outcomeRecord(Long modelid, int quantity, int changetype, Long employeeid,Long deptid,String use,SysUserModel user,String remarks) {
		int t=quantity;
		try {
			HashMap<String, Object> param=new HashMap<>();
			param.put("modelId", modelid);
			List<StoreGoodsInventoryRecords> recordslist=storeGoodsInventoryRecordsMapper.listSelective(param);
			for(StoreGoodsInventoryRecords records:recordslist) {
				if(records.getQuantity()>quantity) {
					records.setQuantity(records.getQuantity()-quantity);
					storeGoodsInventoryRecordsMapper.update(records);
					break;
				}else {
					quantity-=records.getQuantity();
					records.setQuantity(0);
					storeGoodsInventoryRecordsMapper.update(records);
				}
			}
			int sum=storeGoodsInventoryRecordsMapper.countquantity(modelid);
			ModelNumberInfoService.updateQuantity(modelid, sum);
			StockLogService.insertOutcomeLog(modelid, changetype, t,employeeid,deptid,use ,sum ,user.getEmpname(), remarks);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}