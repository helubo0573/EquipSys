package com.stone.equipsys.core.service;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.SysRoleMenu;

/**
 * 系统角色菜单映射表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:03:31
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenu, Long>{

	boolean setRoleMenu(Long id, String nodes);

}
