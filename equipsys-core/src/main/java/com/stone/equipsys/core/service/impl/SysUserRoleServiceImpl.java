package com.stone.equipsys.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.common.util.StringUtil;
import com.stone.equipsys.core.mapper.SysUserRoleMapper;
import com.stone.equipsys.core.domain.SysRoleMenu;
import com.stone.equipsys.core.domain.SysUserRole;
import com.stone.equipsys.core.service.SysUserRoleService;


/**
 * 	系统用户角色映射表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:03:02
 */
 
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long> implements SysUserRoleService {
	
    private static final Logger logger = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);
   
    @Resource
    private SysUserRoleMapper UserRoleMapper;
    
	@Override
	public BaseMapper<SysUserRole, Long> getMapper() {
		return UserRoleMapper;
	}

	@Override
	public boolean setUserRole(Long id, String rolestr) {
		try {
			List<Integer> nodelist=StringUtil.convertStringToIntegerList(rolestr, ",");
			UserRoleMapper.deleteByUserId(id);
			for(Integer node:nodelist) {
				UserRoleMapper.save(new SysUserRole(id,node));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}