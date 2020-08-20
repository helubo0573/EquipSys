package com.stone.equipsys.core.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tool.util.BeanUtil;
import tool.util.StringUtil;

/**
 * 一个系统配置的全局变量缓存机制，但是只适用于单机构系统，对于多机构系统来说，会占用大量内存
 * @author Administrator
 *
 */
public class CacheUtil
{

	private static final Logger logger = Logger.getLogger(CacheUtil.class);

	public static void initSysConfig()
	{

		/*logger.info("初始化系统配置Config...");

		// 系统配置Config缓存
		SysConfigService sysConfigService = (SysConfigService) BeanUtil.getBean("sysConfigService");

		Map<String, Object> configMap = new HashMap<String, Object>();

		List<SysConfig> sysConfigs = sysConfigService.findAll();
		for (SysConfig sysConfig : sysConfigs)
		{
			if (null != sysConfig && StringUtil.isNotBlank(sysConfig.getCode()))
			{
				configMap.put(sysConfig.getCode(), sysConfig.getValue());
			}
		}

		Global.configMap = new HashMap<String, Object>();
		Global.configMap.putAll(configMap);
		logger.info("系统配置完成");*/
	}

}
