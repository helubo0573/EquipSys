package com.stone.equipsys.manage.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.util.DateUtils;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.domain.SysUser;



public class ConLogInterceptor implements HandlerInterceptor
{

	private static final Logger logger = LoggerFactory.getLogger(ConLogInterceptor.class);
	private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		System.out.println("拦截");
		Session session = SecurityUtils.getSubject().getSession();
		if (null == SecurityUtils.getSubject() || session == null)
		{
			return true;
		}
		SysUser userInfo = (SysUser) session.getAttribute("SysUser");
		if (null == userInfo)
		{
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(Constant.RESPONSE_CODE, Constant.NOSESSION_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "您未登录或登录已过期，请登录后再操作");
			ServletUtils.writeToResponse(response, result);
			return false;
		}
		if (userInfo.getStaus().equals("1"))
		{
			Map<String, Object> result = new HashMap<String, Object>();
			result.put(Constant.RESPONSE_CODE, Constant.NOSESSION_CODE_VALUE);
			result.put(Constant.RESPONSE_CODE_MSG, "您的账户已被锁定，请联系管理员解锁");
			ServletUtils.writeToResponse(response, result);
			return false;
		}
		// Debug日志
		if (logger.isDebugEnabled())
		{
			String ip = getRemoteHost(request);
			long beginTime = System.currentTimeMillis();// 开始时间
			startTimeThreadLocal.set(beginTime); // 线程绑定变量（该数据只有当前请求的线程可见）
			logger.debug("{}正在访问URI: {}", ip, request.getRequestURI());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		if (modelAndView != null)
		{
			logger.debug("ViewName: " + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		// 打印JVM信息。
		if (logger.isDebugEnabled())
		{
			long beginTime = startTimeThreadLocal.get();// 1.得到线程绑定的局部变量（开始时间）
			long endTime = System.currentTimeMillis(); // 2、结束时间
			String ip = getRemoteHost(request);
			logger.debug("{}响应结束：  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  剩余内存: {}m  最大可用内存: {}m", ip, DateUtils.formatDateTime(endTime - beginTime), request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024, (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
		}

	}

	private String getRemoteHost(javax.servlet.http.HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
}
