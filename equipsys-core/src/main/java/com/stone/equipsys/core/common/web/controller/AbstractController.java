package com.stone.equipsys.core.common.web.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.stone.equipsys.core.common.util.fastjson.JsonSerializer;
import com.stone.equipsys.core.common.util.json.ObjToJsonSerializer;



public abstract class AbstractController
{

	@Autowired
	private ObjToJsonSerializer objToJsonSerializer;

	// 日志
	protected final Logger logger = Logger.getLogger(this.getClass());

	public ObjToJsonSerializer getObjToJsonSerializer()
	{
		return objToJsonSerializer;
	}

	public void setObjToJsonSerializer(ObjToJsonSerializer objToJsonSerializer)
	{
		this.objToJsonSerializer = objToJsonSerializer;
	}

	protected void jsonResponse(HttpServletResponse response, JsonSerializer jsonSerializer)
	{
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(null));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponse(HttpServletResponse response, Object object)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(null);
		jsonSerializer.addObject(object, null);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(null));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponse(HttpServletResponse response, Object object, String name)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(name);
		jsonSerializer.addObject(object, name);
		response.setContentType("text/html;charset=UTF-8");

		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(name));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponseExclude(HttpServletResponse response, Object object, String name, String[] properties)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(name);
		jsonSerializer.addObjectWithExclude(object, name, properties);
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(name));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponseInclude(HttpServletResponse response, Object object, String name, String[] properties)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(name);
		jsonSerializer.addObjectWithInclude(object, name, properties);
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(name));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponseWithErrorInfo(HttpServletResponse response, Object object, String name, String errorNo, String errorInfo)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(true, name);
		jsonSerializer.addObject(object, name);
		jsonSerializer.setErrorNo(errorNo);
		jsonSerializer.setErrorInfo(errorInfo);
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(name));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponseExcludeWithErrorInfo(HttpServletResponse response, Object object, String name, String errorNo, String errorInfo, String[] properties)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(true, name);
		jsonSerializer.addObjectWithExclude(object, name, properties);
		jsonSerializer.setErrorNo(errorNo);
		jsonSerializer.setErrorInfo(errorInfo);
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(name));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponseIncludeWithErrorInfo(HttpServletResponse response, Object object, String name, String errorNo, String errorInfo, String[] properties)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(true, name);
		jsonSerializer.addObjectWithInclude(object, name, properties);
		jsonSerializer.setErrorNo(errorNo);
		jsonSerializer.setErrorInfo(errorInfo);
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(name));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponseWithErrorinfo(HttpServletResponse response, Object object, String name)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(true, name);
		jsonSerializer.addObject(object, name);
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(name));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponseWithErrorinfo(HttpServletResponse response, Object object)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(true, null);
		jsonSerializer.addObject(object, null);
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(null));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonResponseWithErrorinfo(HttpServletResponse response, String errorNo, String errorInfo)
	{
		PrintWriter out = null;
		JsonSerializer jsonSerializer = new JsonSerializer(true, null);
		jsonSerializer.setErrorNo(errorNo);
		jsonSerializer.setErrorInfo(errorInfo);
		try
		{
			out = response.getWriter();
			out.write(jsonSerializer.toString(null));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void textResponse(HttpServletResponse response, String text)
	{
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(text);
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonSingleObjIncludeResponse(HttpServletResponse response, Object obj, String[] properties)
	{
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(objToJsonSerializer.objectToJsonString(obj));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonSingleObjExcludeResponse(HttpServletResponse response, Object obj, String[] properties)
	{
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(objToJsonSerializer.objectToJsonStringWithExclude(obj, properties));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	protected void jsonSingleObjResponse(HttpServletResponse response, Object obj)
	{
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try
		{
			out = response.getWriter();
			out.write(objToJsonSerializer.objectToJsonString(obj));
			out.flush();
			out.close();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}
}
