package com.stone.equipsys.core.common.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil
{

	private static Logger log = Logger.getLogger(PropertiesUtil.class);

	private static Properties properties;

	private PropertiesUtil()
	{
	}

	static
	{
		properties = new Properties();
		try
		{
			// 读取配置文件
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("sysConfig.properties"));
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error("读取配置文件出错，请确认properties文件已经放在目录下。");
		}
	}

	public static String getValue(String key)
	{
		String value = properties.getProperty(key);
		if (StringUtil.isBlank(value))
		{
			log.info("没有获取指定key的值，请确认资源文件中是否存在【" + key + "】");
		}
		return value;
	}

	public static String getValue(String key, Object[] param)
	{
		String value = getValue(key);
		return MessageFormat.format(value, param);
	}

}
