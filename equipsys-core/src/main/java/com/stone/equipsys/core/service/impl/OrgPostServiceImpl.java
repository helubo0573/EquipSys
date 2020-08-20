package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.OrgPostMapper;
import com.stone.equipsys.core.domain.OrgPost;
import com.stone.equipsys.core.service.OrgPostService;


/**
 * 设备基础信息表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-06 11:23:09
 */
 
@Service("orgPostService")
public class OrgPostServiceImpl extends BaseServiceImpl<OrgPost, Long> implements OrgPostService {
	
    private static final Logger logger = LoggerFactory.getLogger(OrgPostServiceImpl.class);
   
    @Resource
    private OrgPostMapper orgPostMapper;

	@Override
	public BaseMapper<OrgPost, Long> getMapper() {
		return orgPostMapper;
	}

	@Override
	public List<OrgPost> getPosListt(HashMap<String, Object> param) {
		return orgPostMapper.listSelective(param);
	}
	
}