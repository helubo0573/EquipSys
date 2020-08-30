package com.stone.equipsys.core.mapper;

import java.util.HashMap;
import java.util.List;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.mapper.RDBatisDao;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.model.SysUserModel;

/**
 * 	系统用户表Dao
 * 
 * @author stone
 * @version 1.0.0
 * @date 2020-05-28 19:18:59
 */
@RDBatisDao
public interface SysUserMapper extends BaseMapper<SysUser, Long> {

    public SysUser getUserByUserName(String username);

	public List<SysUserModel> getUserExtList(HashMap<String, Object> param);

	public SysUserModel findUserExtById(Long id);

	public void deleteById(Long id);

	public int countUserByUserName(String username);

}
