package com.stone.equipsys.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.mapper.SysRoleMapper;
import com.stone.equipsys.core.mapper.SysRoleMenuMapper;
import com.stone.equipsys.core.mapper.SysUserRoleMapper;
import com.stone.equipsys.core.domain.SysRole;
import com.stone.equipsys.core.service.SysRoleService;


/**
 * 系统角色表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 11:01:24
 */
 
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long> implements SysRoleService {
	
    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);
    
    @Resource
    private SysRoleMenuMapper RoleMenuMapper;
    @Resource
    private SysUserRoleMapper UserRoleMapper;
    @Resource
    private SysRoleMapper RoleMapper;
    
	@Override
	public BaseMapper<SysRole, Long> getMapper() {
		return RoleMapper;
	}

	@Override
	public SysRole getRoleById(Integer roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysRole> getRoleListByUserId(Long Userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean deleteRoleById(Long id) {
		try {
			UserRoleMapper.deleteByRoleId(id);
			RoleMenuMapper.deleteByRoleId(id);
			RoleMapper.deleteRoleById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}