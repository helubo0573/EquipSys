package com.stone.equipsys.core.common.exception;

public class BussinessException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	private String code;

	public BussinessException()
	{
		super();
	}

	public BussinessException(String message)
	{
		super(message);
	}

	public BussinessException(String code, String message)
	{
		super(message);
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

}
