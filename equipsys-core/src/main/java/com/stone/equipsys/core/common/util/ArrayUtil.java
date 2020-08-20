package com.stone.equipsys.core.common.util;

import java.util.Arrays;

public class ArrayUtil
{
	/**
	 * 将两个数组合并为一个
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T> T[] concat(T[] first, T[] second)
	{
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	
	/**
	 * 多个数字合并
	 * @param first
	 * @param rest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] concatAll(T[] first, T[]... rest) {

		 int totalLength = first.length;

		 for (T[] array : rest) {

		  totalLength += array.length;

		 }

		 T[] result = Arrays.copyOf(first, totalLength);

		 int offset = first.length;

		 for (T[] array : rest) {

		  System.arraycopy(array, 0, result, offset, array.length);

		  offset += array.length;

		 }

		 return result;

		}
}
