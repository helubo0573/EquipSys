package com.stone.equipsys.core.mapper;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.SysUserRole;

/**
 * 	系统用户角色映射表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:03:02
 */
@RDBatisDao
public interface SysUserRoleMapper extends BaseMapper<SysUserRole, Long> {

	void deleteByRoleId(Long roleid);

	void deleteByUserId(Long userid);

    

}
