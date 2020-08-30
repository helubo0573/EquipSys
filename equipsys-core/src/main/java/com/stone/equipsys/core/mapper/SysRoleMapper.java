package com.stone.equipsys.core.mapper;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.SysRole;

/**
 * 	系统角色表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 11:01:24
 */
@RDBatisDao
public interface SysRoleMapper extends BaseMapper<SysRole, Long> {

	int deleteRoleById(Long id);

    

}
