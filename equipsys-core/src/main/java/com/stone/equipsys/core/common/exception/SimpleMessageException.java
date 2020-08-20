package com.stone.equipsys.core.common.exception;

public class SimpleMessageException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	private String code;

	public SimpleMessageException()
	{
		super();
	}

	public SimpleMessageException(String message)
	{
		super(message);
	}

	public SimpleMessageException(String code, String message)
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
