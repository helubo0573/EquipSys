package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.SysDataDictionaryMapper;
import com.stone.equipsys.core.domain.SysDataDictionary;
import com.stone.equipsys.core.service.SysDataDictionaryService;


/**
 * 	数据字典ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-08-19 23:35:18
 */
 
@Service("sysDataDictionaryService")
public class SysDataDictionaryServiceImpl extends BaseServiceImpl<SysDataDictionary, Long> implements SysDataDictionaryService {
	
    private static final Logger logger = LoggerFactory.getLogger(SysDataDictionaryServiceImpl.class);
   
    @Resource
    private SysDataDictionaryMapper sysDataDictionaryMapper;

	@Override
	public BaseMapper<SysDataDictionary, Long> getMapper() {
		return sysDataDictionaryMapper;
	}
	
}