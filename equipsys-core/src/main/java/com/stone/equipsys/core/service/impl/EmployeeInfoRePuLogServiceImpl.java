package com.stone.equipsys.core.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EmployeeInfoRePuLogMapper;
import com.stone.equipsys.core.domain.EmployeeInfoRePuLog;
import com.stone.equipsys.core.service.EmployeeInfoRePuLogService;


/**
 * 员工奖惩记录ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-09-16 11:56:52
 */
 
@Service("employeeInfoRePuLogService")
public class EmployeeInfoRePuLogServiceImpl extends BaseServiceImpl<EmployeeInfoRePuLog, Long> implements EmployeeInfoRePuLogService {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeInfoRePuLogServiceImpl.class);
   
    @Resource
    private EmployeeInfoRePuLogMapper employeeInfoRePuLogMapper;

	@Override
	public BaseMapper<EmployeeInfoRePuLog, Long> getMapper() {
		return employeeInfoRePuLogMapper;
	}
	
}