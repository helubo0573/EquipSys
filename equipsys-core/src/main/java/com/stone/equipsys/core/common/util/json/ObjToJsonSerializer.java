package com.stone.equipsys.core.common.util.json;

public interface ObjToJsonSerializer
{

	abstract public String objectToJsonString(Object object, String[] properties, Boolean isFilter);

	abstract public String objectToJsonString(Object object);

	abstract public String objectToJsonStringWithInclude(Object object, String[] properties);

	abstract public String objectToJsonStringWithExclude(Object object, String[] properties);
}
