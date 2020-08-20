package com.stone.equipsys.core.common.exception;

public class ParamValideException extends ErongBaseException
{

	private static final long serialVersionUID = 1L;

	public ParamValideException()
	{
		super();
	}

	public ParamValideException(int code, String msg)
	{
		super(code, msg);
	}

	public ParamValideException(int code)
	{
		super(code);
	}

	public ParamValideException(String message, Throwable cause, int code)
	{
		super(message, cause, code);
	}

	public ParamValideException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ParamValideException(String message)
	{
		super(message);
	}

	public ParamValideException(Throwable cause)
	{
		super(cause);
	}

}
