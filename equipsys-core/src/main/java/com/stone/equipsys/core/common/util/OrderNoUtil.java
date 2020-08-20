package com.stone.equipsys.core.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
/**
 * 格式化订单编号工具
 * @author Administrator
 *
 */
public class OrderNoUtil
{

	private static final String COMMON_DATE = "yyyyMMdd";

	public static String getSerialNumber()
	{
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0)
		{
			hashCode = -hashCode;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(COMMON_DATE);
		return sdf.format(new Date()).substring(2, 8) + String.format("%010d", hashCode);
	}
}
