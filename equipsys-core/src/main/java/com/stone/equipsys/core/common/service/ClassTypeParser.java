package com.stone.equipsys.core.common.service;

public interface ClassTypeParser
{
	<T> T parse(String content, Class<T> valueType);
}
