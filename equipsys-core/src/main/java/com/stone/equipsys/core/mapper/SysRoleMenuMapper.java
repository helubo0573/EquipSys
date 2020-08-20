package com.stone.equipsys.core.mapper;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.SysRoleMenu;

/**
 * 系统角色菜单映射表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:03:31
 */
@RDBatisDao
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu, Long> {

	void deleteByRoleId(Long roleId);

	void deleteByMenuId(Long menuId);

    

}
