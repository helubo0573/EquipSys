package com.stone.equipsys.core.common.web.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

import com.stone.equipsys.core.service.SysMenuService;

import tool.util.StringUtil;

@Service
public class HandInterceptor extends HandlerInterceptorAdapter
{

	private static final Logger log = Logger.getLogger(HandInterceptor.class);

	@Autowired
	@Qualifier("sysMenuService")
	private SysMenuService sysMenuService;

	private HttpServletRequest request;

	private MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception
	{
		this.request = request;
		String methodName = methodNameResolver.getHandlerMethodName(request);
		this.saveOperatorLog(methodName);
		if (e != null)
		{
			log.error("Exception message   :", e);
			this.saveExceptionLog(methodName, e);
		}
	}

	public void saveOperatorLog(String methodName)
	{

	}

	public void saveExceptionLog(String methodName, Exception e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));

	}

	public boolean isOperatorLog(String path)
	{
		String[] urlArr = { "Add.htm", "Edit.htm", "Delete.htm" };
		for (int i = 0; i < urlArr.length; i++)
		{
			String url = urlArr[i];
			int size = path.lastIndexOf(url);
			if (size > 0)
			{
				return true;
			}
		}
		return false;
	}

	protected String getAllParams(boolean safety)
	{
		StringBuilder ps = new StringBuilder();
		Enumeration<?> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements())
		{
			String parameter = (String) parameterNames.nextElement();
			String value = request.getParameter(parameter);
			if (StringUtil.isNotBlank(value))
			{
				if (safety || (!parameter.contains("password") && !parameter.contains("pwd")))
				{ // 安全性
					ps.append(parameter + "=" + value);
					if (parameterNames.hasMoreElements())
					{
						ps.append("&");
					}
				}
			}
		}
		return ps.toString();
	}
}
