package com.stone.equipsys.core.service;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.OrgDept;

/**
 * 设备基础信息表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-06 11:22:46
 */
public interface OrgDeptService extends BaseService<OrgDept, Long>{

	int deleteDept(String id);

}
