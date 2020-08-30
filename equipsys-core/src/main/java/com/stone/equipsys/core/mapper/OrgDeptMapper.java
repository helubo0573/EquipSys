package com.stone.equipsys.core.mapper;

import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.OrgDept;

/**
 * 	设备基础信息表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-06-06 11:22:46
 */
@RDBatisDao
public interface OrgDeptMapper extends BaseMapper<OrgDept, Long> {

    List<OrgDept> findByDept();

	int deleteById(long parseLong);

	List<OrgDept> findByParentDept();
}
