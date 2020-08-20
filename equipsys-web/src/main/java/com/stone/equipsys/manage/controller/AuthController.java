package com.stone.equipsys.manage.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stone.equipsys.core.common.constant.Constant;
import com.stone.equipsys.core.common.exception.ImgCodeException;
import com.stone.equipsys.core.common.exception.ParamValideException;
import com.stone.equipsys.core.common.exception.ServiceException;
import com.stone.equipsys.core.common.util.ServletUtils;
import com.stone.equipsys.core.common.web.controller.BaseController;
import com.stone.equipsys.core.domain.SysMenu;
import com.stone.equipsys.core.domain.SysUser;
import com.stone.equipsys.core.model.SysUserModel;
import com.stone.equipsys.core.service.SysUserService;
import com.stone.equipsys.core.system.security.PasswordEncoder;
import com.stone.equipsys.manage.Action.AuthAction;
import com.stone.equipsys.manage.Action.SysUserAction;


@Scope("prototype")
@Controller
public class AuthController extends BaseController{
	
	@Resource
	private AuthenticationManager authenticationManager;
	@Resource
	private PasswordEncoder passwordEncoder;// 密码加密
	@Resource
	private SysUserService userService;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	@RequestMapping(value="user/login")
	public void Login(HttpServletResponse response, HttpServletRequest request,HttpSession session,
			@RequestParam(value="username",required=true)String username,
			@RequestParam(value="password",required=true)String password,
			@RequestParam(value="checkcode",required=true)String checkcode){
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			AuthAction.checkImgCode(checkcode, session.getAttribute("code"));
			Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			Subject user = SecurityUtils.getSubject();
			//获取加密后的密码字符串
			password = passwordEncoder.encodePassword(String.valueOf(password));
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			//token.setRememberMe(true);
			user.login(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//还需要研究这一条
			session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
			SysUserModel userinfo=(SysUserModel) user.getSession().getAttribute("SysUser");
			session.setAttribute("SysUser", userinfo);
			
			
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "登录成功");
		} catch (ImgCodeException e) {
			logger.error(e.getMessage(), e);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "图片验证码错误");
		}catch (IncorrectCredentialsException ex){
			logger.error("密码错误", ex);
			res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "密码错误请重新输入");
		} catch (LockedAccountException ex){
			logger.error("该用户已锁定", ex);
			res.put(Constant.RESPONSE_CODE, Constant.OTHER_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "该用户已锁定，请联系管理员！");
		} catch (AuthenticationException ex){
			logger.error("登录失败", ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "用户名或密码错误");
		} catch (ExpiredCredentialsException ex){
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, ex.getMessage());
		} catch (UnknownAccountException ex){
			logger.error(ex.getMessage(), ex);
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, "账号不存在请核对后重新输入");
		}
		ServletUtils.writeToResponse(response, res);
	}
	
	@RequestMapping(value="user/getimgcode")
	public void getImgCode() throws Exception {
		super.generateImgCode();
	}
	@RequestMapping(value="user/loginout")
	public void outlogin() {
		
	}
}
