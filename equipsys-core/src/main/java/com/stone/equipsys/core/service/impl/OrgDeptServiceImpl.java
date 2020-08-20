package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EmployeeInfoMapper;
import com.stone.equipsys.core.mapper.OrgDeptMapper;
import com.stone.equipsys.core.domain.OrgDept;
import com.stone.equipsys.core.service.OrgDeptService;


/**
 * 设备基础信息表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-06 11:22:46
 */
 
@Service("orgDeptService")
public class OrgDeptServiceImpl extends BaseServiceImpl<OrgDept, Long> implements OrgDeptService {
	
    private static final Logger logger = LoggerFactory.getLogger(OrgDeptServiceImpl.class);
   
    @Resource
    private OrgDeptMapper orgDeptMapper;
    @Resource
    private EmployeeInfoMapper empmapper;

	@Override
	public BaseMapper<OrgDept, Long> getMapper() {
		return orgDeptMapper;
	}

	@Override
	public int deleteDept(String id) {
		HashMap<String, Object> mp=new HashMap<String,Object>();
		return 0;
	}

}