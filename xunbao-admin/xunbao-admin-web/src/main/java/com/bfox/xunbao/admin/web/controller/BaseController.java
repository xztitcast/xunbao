package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.admin.web.entity.SysUserTenant;
import com.bfox.xunbao.admin.web.service.impl.UserDetailsServiceImpl.LoginUserDetails;

import com.bfox.xunbao.admin.web.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 权限基础控制器
 * @author eden
 * @time 2022年7月22日 上午11:36:07
 */
public abstract class BaseController {

	/**
	 * 获取登陆详情
	 * @return
	 */
	protected LoginUserDetails getLoginUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (LoginUserDetails) authentication.getPrincipal();
	}

	/**
	 * 获取用户信息
	 * @return SysUser
	 */
	protected SysUser getUser() {
		return getLoginUserDetails().getSysUser();
	}

	/**
	 * 获取用户租户信息
	 * @return
	 */
	protected SysUserTenant getUserTenant() {
		return getLoginUserDetails().getSysUserTenant();
	}

	/**
	 * 获取用户ID
	 * @return Long
	 */
	protected Long getUserId() {
		return getUser().getId();
	}

	/**
	 * 获取用户名
	 * @return String
	 */
	protected String getUsername() {return getUser().getUsername();}

	/**
	 * 获取租户ID
	 * @return
	 */
	protected Long getTenantId() {
		return getUserTenant().getTenantId();
	}

	/**
	 * 获取租户名称
	 * @return
	 */
	protected String getTenantName() {
		return getUserTenant().getTenantName();
	}

}
