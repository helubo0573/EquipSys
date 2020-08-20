package com.stone.equipsys.core.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.EmployeeInfo;
import com.stone.equipsys.core.model.EmployeeInfoModel;

/**
 * 设备基础信息表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-05 22:59:03
 */
public interface EmployeeInfoService extends BaseService<EmployeeInfo, Long>{

	List<EmployeeInfo> getEmployeeTree(HashMap<String,Object> param);

	Page<EmployeeInfo> searchEmployee(HashMap<String,Object> params,int currentPage,int pageSize);
	
	Page<EmployeeInfoModel> searchExtEmployee(HashMap<String,Object> params,int currentPage,int pageSize);

	Long saveEmployeeInfo(HttpServletRequest request);

	int dimissionEmployee(Long id);	//员工离职
}
