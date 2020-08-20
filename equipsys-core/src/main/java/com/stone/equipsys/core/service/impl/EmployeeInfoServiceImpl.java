package com.stone.equipsys.core.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.EmployeeInfoMapper;
import com.stone.equipsys.core.model.EmployeeInfoModel;
import com.stone.equipsys.core.domain.EmployeeInfo;
import com.stone.equipsys.core.service.EmployeeInfoService;


/**
 * 设备基础信息表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-05 22:59:03
 */
 
@Service("employeeInfoService")
public class EmployeeInfoServiceImpl extends BaseServiceImpl<EmployeeInfo, Long> implements EmployeeInfoService {
	
    private static final Logger logger = LoggerFactory.getLogger(EmployeeInfoServiceImpl.class);
   
    @Resource
    private EmployeeInfoMapper employeeInfoMapper;

	@Override
	public BaseMapper<EmployeeInfo, Long> getMapper() {
		return employeeInfoMapper;
	}

	@Override
	public List<EmployeeInfo> getEmployeeTree(HashMap<String,Object> param) {
		return employeeInfoMapper.listSelective(param);
	}

	@Override
	public Page<EmployeeInfo> searchEmployee(HashMap<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<EmployeeInfo> list=employeeInfoMapper.listSelective(params);
		return (Page<EmployeeInfo>) list;
	}

	@Override
	public Page<EmployeeInfoModel> searchExtEmployee(HashMap<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<EmployeeInfoModel> list=employeeInfoMapper.searchExtEmployee(params);
		return (Page<EmployeeInfoModel>) list;
	}

	@Override
	public Long saveEmployeeInfo(HttpServletRequest request) {
		try {
			EmployeeInfo employee=new EmployeeInfo(request.getParameter("empinfo-id"), 
					request.getParameter("empinfo-name"), 
					request.getParameter("empinfo-sex"), 
					request.getParameter("empinfo-deptid"), 
					request.getParameter("empinfo-postid"), 
					request.getParameter("empinfo-mobil"), 
					request.getParameter("empinfo-indate"), 
					request.getParameter("empinfo-remarks"));
			if(request.getParameter("empinfo-id")=="") {
				employeeInfoMapper.save(employee);
			}else {
				employeeInfoMapper.update(employee);
			}
			return employee.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		}
	}

	@Override
	public int dimissionEmployee(Long id) {
		
		return employeeInfoMapper.dimissionEmployee(id);
	}
	
}