package com.stone.equipsys.core.system.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

import tool.util.StringUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class SpringSecurityAuthorizeUrlDirective implements TemplateDirectiveModel{

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] temp, TemplateDirectiveBody body) throws TemplateException, IOException
	{
		if (params.isEmpty())
		{
			throw new TemplateModelException("This directive doesn't allow parameters.");
		}

		if (body != null)
		{
			boolean b = false;
			String url = params.get("value").toString();
			if (StringUtil.isNotBlank(url))
			{
				// XXX 增加对用户是否登录的判断
				UserRole userRole = (UserRole) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				Map<String, UserFunction> resourceMap = userRole.getFunctionMap();
				if (resourceMap != null && !resourceMap.isEmpty())
				{
					if (resourceMap.containsKey(url))
					{
						b = true;
					}
				}
			}
			if (b)
			{
				body.render(env.getOut());
			}
		} else
		{
			throw new NullPointerException("missing body");
		}
	}
}
