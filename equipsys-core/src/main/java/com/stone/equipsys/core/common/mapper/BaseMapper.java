package com.stone.equipsys.core.common.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 基础mapper处理接口
 * @author Administrator
 * @param <T>
 * @param <ID>
 */
@RDBatisDao
public interface BaseMapper<T, ID extends Serializable> {

	int save(T e);

	void saveRecord(T e);

	int update(T e);

	int updateSelective(Map<String, Object> paramMap);

	T findSelective(Map<String, Object> paramMap);

	T findByPrimary(ID primary);

	List<T> listSelective(Map<String, Object> paramMap);
}
