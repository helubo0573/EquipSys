package com.stone.equipsys.core.common.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.exception.BussinessException;
import com.stone.equipsys.core.common.exception.ParamValideException;
import com.stone.equipsys.core.common.exception.ServiceException;
import com.stone.equipsys.core.common.exception.SimpleMessageException;
import com.stone.equipsys.core.common.model.URLConfig;
import com.stone.equipsys.core.common.util.JsonUtil;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.util.ValidateCode;
import com.stone.equipsys.core.domain.SysRole;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.service.SysRoleService;
import com.stone.equipsys.core.service.SysUserService;

import tool.util.NumberUtil;
import tool.util.StringUtil;



@Controller
@Scope("prototype")
public abstract class BaseController extends AbstractController
{

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@Autowired
	protected URLConfig mlmsAppServerConfig;
	@Resource
	protected SysUserService userservice;
	@Resource
	protected SysRoleService roleService;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	@InitBinder
	protected final void initBinderInternal(WebDataBinder binder)
	{
		registerDefaultCustomDateEditor(binder);
		registerDefaultCustomNumberEditor(binder);
		initBinder(binder);
	}

	private void registerDefaultCustomNumberEditor(WebDataBinder binder)
	{
		// 注册双精度数字格式化类型: #0.00
		NumberFormat numberFormat = new DecimalFormat("#0.00");
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, numberFormat, true));
	}

	protected void registerDefaultCustomDateEditor(WebDataBinder binder)
	{
		// 注册默认的日期格式化类型: yyyy-MM-dd
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	protected void initBinder(WebDataBinder binder)
	{
	}
	/**
	 * 	判断session中是否装载了用户对象
	 * @param request
	 * @return
	 */
	protected SysUser getLoginUser(HttpServletRequest request)
	{
		Object obj = request.getSession().getAttribute(Constant.SESSION_PROPERTY_USER);
		if (obj != null)
		{
			return (SysUser) obj;
		}
		return null;
	}
	/**
	 * 	从shiro中获取到用户对象信息
	 * @return
	 * @throws ServiceException
	 */
	protected SysUser getSysUser() throws ServiceException
	{
		// 增加用户登录判断
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SysUser sysUser = userservice.getUserByUserName(user.getUsername());
		return sysUser;
	}

	protected void setToken(String tokenName, HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			session = request.getSession();
		}
		session.setAttribute(tokenName, "true");
	}

	protected String isToken(String tokenName, HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			session = request.getSession();
		}
		String tokenValue = (String) session.getAttribute(tokenName);
		String paramValue = (String) request.getParameter(tokenName);

		// 参数、session中都没用token值提示错误
		if (StringUtil.isBlank(paramValue) && StringUtil.isBlank(tokenValue))
		{
			return "会话Token未设定！";
		} else if (StringUtil.isBlank(paramValue) && !StringUtil.isBlank(tokenValue))
		{
			return "表单Token未设定！";
		} else if (paramValue.equals(tokenValue) && !StringUtil.isBlank(tokenValue) && "true".equals(tokenValue))
		{ // session中有token,防止重复提交检查
			session.setAttribute(tokenName, "false");
			return "";
		} else
		{
			return "请勿重复提交！";
		}
	}

	protected void message(HttpServletResponse response) throws IOException
	{
		this.message(response, "", true);
	}

	protected void message(HttpServletResponse response, String msg) throws IOException
	{
		this.message(response, msg, true);
	}

	protected void message(HttpServletResponse response, String msg, boolean result) throws IOException
	{
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("success", result);
		data.put("msg", msg);
		this.jsonResponse(response, data);
	}

	protected Integer paramInt(HttpServletRequest request, String str)
	{
		return NumberUtil.getInt(request.getParameter(str));
	}

	protected Long paramLong(HttpServletRequest request, String str)
	{
		return NumberUtil.getLong(request.getParameter(str));
	}

	protected String paramString(HttpServletRequest request, String str)
	{
		return StringUtil.isNull(request.getParameter(str));
	}

	protected String redirect(String url)
	{
		return "redirect:" + mlmsAppServerConfig + url;
	}

	protected String redirectLogin()
	{
		return redirect("/modules/login.htm");
	}

	protected String success()
	{
		return redirect("/success.htm");
	}

	protected String error()
	{
		return redirect("/error.htm");
	}

	protected String success(ModelMap model)
	{
		return "success";
	}

	protected String error(ModelMap model)
	{
		return "error";
	}
	/**
	 * 判断用户是否允许访问请求
	 * @param url
	 * @return
	 *//*
	protected boolean isAllowAccess(String url)
	{
		Map<String, UserFunction> functionMap = ((UserRole) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFunctionMap();
		if (functionMap.containsKey(url))
		{
			return true;
		}
		return false;
	}*/

	@ExceptionHandler({ AuthorizationException.class })
	public void authorizationExceptionHandler(AuthorizationException e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.PERM_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "对不起，您没有该权限");
		ServletUtils.writeToResponse(response, res);
	}

	@ExceptionHandler({ Exception.class })
	public void exceptionHandler(Exception e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, "400");
		res.put(Constant.RESPONSE_CODE_MSG, "系统出错了，请检查参数是否正确");
		logger.error("Exception:", e);
		ServletUtils.writeToResponse(response, res);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public void notNullException(MethodArgumentNotValidException e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, "400");
		BindingResult bindingResult = e.getBindingResult();
		if (bindingResult.hasErrors())
		{
			String msg = bindingResult.getFieldError().getDefaultMessage();
			res.put(Constant.RESPONSE_CODE_MSG, msg);
		} else
		{
			res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		}
		logger.error("MethodArgumentNotValidException:", e);
		ServletUtils.writeToResponse(response, res);
	}

	@ExceptionHandler({ ParamValideException.class })
	public void paramValideException(ParamValideException e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, "404");
		res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
		logger.error("MethodArgumentNotValidException:", e);
		ServletUtils.writeToResponse(response, res);
	}

	@ExceptionHandler({ ServiceException.class })
	public void excptionDispose(ServiceException e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, e.getCode());
		res.put(Constant.RESPONSE_CODE_MSG, "操作失败");

		logger.error("ServiceException:", e);

		ServletUtils.writeToResponse(response, res);
	}

	@ExceptionHandler({ RuntimeException.class })
	public void runtimeExcptionDispose(RuntimeException e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "系统出错了");

		logger.error("RuntimeException", e);

		ServletUtils.writeToResponse(response, res);
	}

	@ExceptionHandler({ BussinessException.class })
	public void bussinessException(BussinessException e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(e.getCode()))
		{
			res.put(Constant.RESPONSE_CODE, e.getCode());
		} else
		{
			res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
		}
		res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());

		logger.error("BussinessException", e);

		ServletUtils.writeToResponse(response, res);
	}

	@ExceptionHandler({ SimpleMessageException.class })
	public void simpleMessageException(SimpleMessageException e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(e.getCode()))
		{
			res.put(Constant.RESPONSE_CODE, e.getCode());
		} else
		{
			res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
		}
		res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());

		logger.error("SimpleMessageException", e);

		ServletUtils.writeToResponse(response, res);
	}

	@ExceptionHandler({ BindException.class })
	public void bindException(BindException e, HttpServletResponse response)
	{
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
		res.put(Constant.RESPONSE_CODE_MSG, "数据保存失败，请稍后重试");

		logger.error("参数校验失败" + e.getFieldError().getDefaultMessage(), e);

		ServletUtils.writeToResponse(response, res);
	}

	public List<Integer> getRole(HttpServletRequest request)
	{

		List<Integer> roles = new ArrayList<Integer>();
		HttpSession session = request.getSession();
		Integer role = (Integer) session.getAttribute(Constant.ROLEID);
		roles.add(role);

		return roles;

	}
	/**
	 * 	获取用户的角色对象
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public SysRole getRoleForLoginUser(HttpServletRequest request) throws ServiceException
	{
		HttpSession session = request.getSession();
		Integer roleId = (Integer) session.getAttribute(Constant.ROLEID);
		if (null == roleId)
		{
			return new SysRole();
		}
		SysRole role = roleService.getRoleById(roleId);

		return role;

	}

	public String getLoginUserName(HttpServletRequest request) throws ServiceException
	{
		SysUser loginUser = getLoginUser(request);

		return loginUser.getUsername();

	}

	public Map<String, Object> getRequestFormMap(HttpServletRequest request) throws UnsupportedEncodingException
	{
		String str = getRequestParams(request);
		Map<String, Object> params = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements())
		{
			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1)
			{
				String paramValue = paramValues[0];
				if (paramValue.length() != 0)
				{
					params.put(paramName, paramValue);
				}
			}
		}
		if (StringUtil.isNotBlank(str) && params.size() == 0)
		{
			String str1 = java.net.URLDecoder.decode(str, "UTF-8");
			String[] strs = str.split("name=");
			String[] strs1 = str1.split("&");
			for (int i = 1; i < strs.length; i++)
			{
				String temp = strs[i].substring(0, strs[i].indexOf("------"));
				int index = temp.indexOf("\"", 1);
				index = index + 1;
				String key = temp.substring(0, index);
				String value = temp.substring(index, temp.length());
				params.put(key, value);
			}
			for (int i = 0; i < strs1.length; i++)
			{
				String[] temp = strs1[i].split("=");
				params.put(temp[0], temp[1]);
			}
		}
		return params;
	}

	public String getRequestParams(HttpServletRequest request)
	{
		String params = "";
		try
		{
			request.setCharacterEncoding("UTF-8");
			InputStream in = request.getInputStream();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
			params = sb.toString();
		} catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		return params;
	}

	protected void generateImgCode() throws Exception
	{
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		HttpSession session = request.getSession();

		ValidateCode vCode = new ValidateCode(120, 40, 4, 50);
		session.setAttribute("code", vCode.getCode());
		vCode.write(response.getOutputStream());
		response.getOutputStream().flush();
	}

	protected Map<String, Object> getParams(HttpServletRequest request)
	{
		Map<String, String[]> rec = request.getParameterMap();
		Map<String, Object> result = new LinkedHashMap<>();
		for (Map.Entry<String, String[]> entry : rec.entrySet())
		{
			String name = entry.getKey();
			Object value = entry.getValue()[0];
			result.put(name, value);
		}
		return result;
	}

	protected void writeHtml(Map<? extends Object, Object> res)
	{
		response.addHeader("content-type", "text/javascript");

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		OutputStreamWriter out = null;
		try
		{
			out = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			logger.error("系统出错：" + e.getMessage());
		} catch (IOException e)
		{
			logger.error("系统出错：" + e.getMessage());
		}
		JsonUtil.write(out, res);
	}
}
