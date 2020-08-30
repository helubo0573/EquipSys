package com.stone.equipsys.core.service;

import java.util.List;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.SysRole;

/**
 * 	系统角色表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 11:01:24
 */
public interface SysRoleService extends BaseService<SysRole, Long>{

	SysRole getRoleById(Integer roleId);

	List<SysRole> getRoleListByUserId(Long Userid);

	boolean deleteRoleById(Long id);

}
