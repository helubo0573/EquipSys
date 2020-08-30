package com.stone.equipsys.core.mapper;

import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.SysMenu;

/**
 * 	系统菜单资源表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 11:15:08
 */
@RDBatisDao
public interface SysMenuMapper extends BaseMapper<SysMenu, Long> {

	List<SysMenu> getPermsByUser(String username);

	List<SysMenu> getMenuByUserName(String username);

	List<SysMenu> getMenu();

	List<SysMenu> getParentMenu();

	void deleteById(Long id);

	List<SysMenu> getMenuAll();

	List<SysMenu> getAdminPerms();

    

}
