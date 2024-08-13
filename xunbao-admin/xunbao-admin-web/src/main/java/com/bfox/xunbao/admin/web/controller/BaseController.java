package com.bfox.xunbao.admin.web.controller;

import com.alibaba.fastjson2.JSON;
import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.support.SecuritySubjectManager;

import java.util.List;

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

	/**
	 * 获取租户ID
	 * @return
	 */
	protected Long getTenantId() {
		return getUser().getTenantId();
	}

	/**
	 * 获取租户名称
	 * @return
	 */
	protected String getTenantName() {
		return getUser().getTenantName();
	}

	/**
	 * 获取父子租户ID列表
	 * @return
	 */
	protected List<Long> getTenantIdList() {
		String tenantIds = getUser().getTenantIds();
		return JSON.parseArray(tenantIds, Long.class);
	}

}
