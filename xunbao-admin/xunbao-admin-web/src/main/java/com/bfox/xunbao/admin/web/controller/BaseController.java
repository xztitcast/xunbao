package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.support.SecuritySubjectManager;

/**
 * 权限基础控制器
 * @author eden
 * @time 2022年7月22日 上午11:36:07
 */
public abstract class BaseController {

	/**
	 * 获取用户信息
	 * @return SysUser
	 */
	protected SysUser getUser() {
		return SecuritySubjectManager.getSubject();
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

}
