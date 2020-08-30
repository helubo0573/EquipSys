package com.stone.equipsys.core.system.realm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.stone.equipsys.core.domain.SysMenu;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.mapper.SysMenuMapper;
import com.stone.equipsys.core.model.SysUserModel;
import com.stone.equipsys.core.service.SysMenuService;
import com.stone.equipsys.core.service.SysUserService;



public class ShiroRealm extends AuthorizingRealm{

	private static final Logger logger = Logger.getLogger(ShiroRealm.class);

	@Resource
	private SysUserService UserService;
	@Resource	
	private SysMenuService MenuService;
	@Override
	/**
	 * 	授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		logger.info("授权认证：" + principals.getRealmNames());
		// (关闭浏览器，或超时)非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		if (!SecurityUtils.getSubject().isAuthenticated()){
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		SysUser user=(SysUser) principals.oneByType(SysUser.class);
		List<SysMenu> perms=MenuService.getPermsByUser(user);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (SysMenu perm : perms)
		{
			// 基于Permission的权限信息
			info.addStringPermission(perm.getPerms());
		}
		return info;
	}

	/**
	 * 	验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
	{
		logger.info("验证用户");
		// 获取基于用户名和密码的令牌
		// user.login(token) 间接调用
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 从数据库中查询用户用信息
		SysUserModel user=UserService.getUserByUserName(token.getUsername());
		if (user == null){
			throw new UnknownAccountException();// 没找到帐号
		} else if (user.getStaus() == 2){
			throw new LockedAccountException(); // 帐号锁定
		} else{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			session.setAttribute("SysUser", user);
			List<Object> principals = new ArrayList<Object>();//用一个MAP对象将用户类和名称直接传到SimpleAuthenticationInfo类的第一个参数中
			principals.add(user.getUsername());
			principals.add(user);
			//return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
			return new SimpleAuthenticationInfo(principals, user.getPassword(), getName());
		}

	}

}
