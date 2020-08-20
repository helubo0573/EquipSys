package com.stone.equipsys.core.common.model;

import tool.util.StringUtil;
/**
 * 生成url的方法，一般用法应该是生成重定向页面
 * @author Administrator
 *
 */
public class URLConfig {

	private String protocol = "http";

	private String host;

	private Integer port = 80;

	private String url = null;

	private String contextPath = "";

	public void init()
	{
		if (url == null)
			createUrl();
	}

	private void createUrl()
	{
		StringBuilder sb = new StringBuilder();
		if (StringUtil.isNotBlank(protocol))
		{
			sb.append(protocol).append("://");
		}
		if (StringUtil.isNotBlank(host))
		{
			sb.append(host);
		}
		if (port != null)
		{
			if (("http".equals(protocol) && port == 80) || ("https".equals(protocol) && port == 443))
			{

			} else
			{
				sb.append(":").append(port);
			}
		}

		if (contextPath == null || "".equals(contextPath))
		{

		} else
		{
			if (contextPath.charAt(0) == '/')
			{
				sb.append(contextPath);
			} else
			{
				sb.append("/" + contextPath);
			}
		}

		while (sb.charAt(sb.length() - 1) == '/')
		{
			{
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		url = sb.toString();
	}

	public String toString()
	{
		if (url == null)
		{
			createUrl();
		}
		return url;
	}

	public String getHost()
	{
		return host;
	}

	public void setHost(String host)
	{
		this.host = host.toLowerCase();
	}

	public Integer getPort()
	{
		return port;
	}

	public void setPort(Integer port)
	{
		this.port = port;
	}

	public String getProtocol()
	{
		return protocol;
	}

	public void setProtocol(String protocol)
	{
		this.protocol = protocol.toLowerCase();
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getContextPath()
	{
		return contextPath;
	}

	public void setContextPath(String contextPath)
	{
		this.contextPath = contextPath;
	}
}
