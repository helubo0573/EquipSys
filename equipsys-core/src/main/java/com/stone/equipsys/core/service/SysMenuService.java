package com.stone.equipsys.core.service;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.service.BaseService;
import com.stone.equipsys.core.domain.SysMenu;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.model.MenuModel;

/**
 *	 系统菜单资源表Service
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 11:15:08
 */
public interface SysMenuService extends BaseService<SysMenu, Long>{

	List<SysMenu> queryList(HashMap<String, Object> hashMap);	//查询所有

	List<MenuModel> getMenuListByRoleIds(List<Long> roleIdList);

	List<SysMenu> getPermsByUser(SysUser user);	//根据用户名获取授权列表

	List<SysMenu> getMenuByUserName(SysUser user);

	List<SysMenu> getMenu();

	List<SysMenu> getParentMenu();

	boolean deleteMenu(Long id);

}
