package com.stone.equipsys.core.service.impl;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.SysMenuMapper;
import com.stone.equipsys.core.mapper.SysRoleMenuMapper;
import com.stone.equipsys.core.model.MenuModel;
import com.stone.equipsys.core.domain.SysMenu;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.service.SysMenuService;


/**
 * 系统菜单资源表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 11:15:08
 */
 
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Long> implements SysMenuService {
	
    private static final Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);
   
    @Resource
    private SysMenuMapper MenuMapper;
    @Resource
    private SysRoleMenuMapper RoleMenuMapper;
	@Override
	public BaseMapper<SysMenu, Long> getMapper() {
		return MenuMapper;
	}

	@Override
	public List<SysMenu> queryList(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuModel> getMenuListByRoleIds(List<Long> roleIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysMenu> getPermsByUser(SysUser user) {
		if(user.getIsadmin()==1) {
			return MenuMapper.getAdminPerms();
		}else {
			return MenuMapper.getPermsByUser(user.getUsername());
		}
	}

	@Override
	public List<SysMenu> getMenuByUserName(SysUser user) {
		if(user.getIsadmin()!=1)
			return MenuMapper.getMenuByUserName(user.getUsername());
		else {
			return MenuMapper.getMenuAll();
		}
		
	}

	@Override
	public List<SysMenu> getMenu() {
		return MenuMapper.getMenu();
	}

	@Override
	public List<SysMenu> getParentMenu() {
		// TODO Auto-generated method stub
		return MenuMapper.getParentMenu();
	}

	@Override
	@Transactional
	public boolean deleteMenu(Long id) {
		try {
			RoleMenuMapper.deleteByMenuId(id);
			MenuMapper.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}