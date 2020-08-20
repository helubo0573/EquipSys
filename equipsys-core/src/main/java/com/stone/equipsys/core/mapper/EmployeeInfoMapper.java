package com.stone.equipsys.core.mapper;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.EmployeeInfo;
import com.stone.equipsys.core.model.EmployeeInfoModel;

/**
 * 设备基础信息表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-05 22:59:03
 */
@RDBatisDao
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo, Long> {

	List<EmployeeInfo> getEmployeeTree();

	List<EmployeeInfoModel> searchExtEmployee(HashMap<String, Object> params);

	EmployeeInfoModel findEmployeeExtInfoById(Long id);

	int dimissionEmployee(Long id);

}
