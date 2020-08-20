package com.stone.equipsys.core.common.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.stone.equipsys.core.common.mapper.BaseMapper;
import com.stone.equipsys.core.common.service.BaseService;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	@Resource
	private BaseMapper<T, ID> baseMapper;

	public abstract BaseMapper<T, ID> getMapper();
	
	/**
	 * insert方法 
	 */
	public int insert(T record)
	{
		return getMapper().save(record);
	}
	/**
	 * 获取关键字
	 */
	public T getById(ID id)
	{
		return getMapper().findByPrimary(id);
	}
	
	/**
	 * update方法
	 */
	public int updateById(T record)
	{
		return getMapper().update(record);
	}
	

}
