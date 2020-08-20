package com.stone.equipsys.core.common.util.json;

public abstract class AbstractJsonSerializer
{

	private String errorNo = "0";

	private String errorInfo = "";

	private boolean needErrorInfo = false;

	abstract public void addObject(Object object, String name, String[] properties, Boolean isFilter);

	abstract public void addObject(Object object, String name);

	abstract public void addObjectWithExclude(Object object, String name, String[] properties);

	abstract public void addObjectWithInclude(Object object, String name, String[] properties);

	abstract public String toString();

	public String getErrorNo()
	{
		return errorNo;
	}

	public void setErrorNo(String errorNo)
	{
		this.errorNo = errorNo;
	}

	public String getErrorInfo()
	{
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo)
	{
		this.errorInfo = errorInfo;
	}

	public boolean isNeedErrorInfo()
	{
		return needErrorInfo;
	}

	public void setNeedErrorInfo(boolean needErrorInfo)
	{
		this.needErrorInfo = needErrorInfo;
	}

}
