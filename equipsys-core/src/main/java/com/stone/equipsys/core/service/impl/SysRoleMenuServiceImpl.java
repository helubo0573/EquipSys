package com.stone.equipsys.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.impl.BaseServiceImpl;
import com.stone.equipsys.core.common.util.StringUtil;
import com.stone.equipsys.core.mapper.SysRoleMenuMapper;
import com.stone.equipsys.core.domain.SysRoleMenu;
import com.stone.equipsys.core.service.SysRoleMenuService;


/**
 * 系统角色菜单映射表ServiceImpl
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:03:31
 */
 
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu, Long> implements SysRoleMenuService {
	
    private static final Logger logger = LoggerFactory.getLogger(SysRoleMenuServiceImpl.class);
   
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public BaseMapper<SysRoleMenu, Long> getMapper() {
		return sysRoleMenuMapper;
	}

	@Override
	@Transactional
	public boolean setRoleMenu(Long id, String nodes) {
		try {
			System.out.println("id::::"+id);
			List<Integer> nodelist=StringUtil.convertStringToIntegerList(nodes, ",");
			sysRoleMenuMapper.deleteByRoleId(id);
			if(nodelist!=null)
				for(Integer node:nodelist) {
					sysRoleMenuMapper.save(new SysRoleMenu(id,node));
				}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}