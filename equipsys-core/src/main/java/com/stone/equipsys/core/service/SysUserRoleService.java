package com.stone.equipsys.core.service;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.SysUserRole;

/**
 * 系统用户角色映射表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:03:02
 */
public interface SysUserRoleService extends BaseService<SysUserRole, Long>{

	boolean setUserRole(Long id, String rolestr);

}
