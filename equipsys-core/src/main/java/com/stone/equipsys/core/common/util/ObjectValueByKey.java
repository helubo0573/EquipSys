package com.stone.equipsys.core.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class ObjectValueByKey {

	private static final Logger logger = Logger.getLogger(Tree.class);
	
	/**
	 * 用field方法得到对象的键值
	 * @param object
	 * @param key
	 * @return
	 */
	public static Object ValueByField(Object object,String key) {
		Field[] fields = object.getClass().getDeclaredFields();
		Object value = null;
		for (Field field : fields){
			field.setAccessible(true);
				try{
					value=field.getName().equals(key)?field.get(object):null;
				} catch (Exception e){
					logger.error(e);
				}
			//}
		}
		return value;
	}
	
	/**
	 * 用method方法得到对象的键值
	 * @param object
	 * @param key
	 * @return
	 */
	public static Object ValueByMethod(Object object,String key) {
		String getMethodName = "get" + StringUtil.ForMat(key);
		Object value = null;
		Method method = null;
		try{
			method = object.getClass().getMethod(getMethodName);
		} catch (Exception e){
			logger.error(e);
		}
		if (method != null){
			try{
				value = method.invoke(object);
			} catch (Exception e){
				logger.error(e);
			}
		}
		return value;
	}
}
