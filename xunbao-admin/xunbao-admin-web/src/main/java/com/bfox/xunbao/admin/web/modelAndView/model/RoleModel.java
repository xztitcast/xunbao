package com.bfox.xunbao.admin.web.modelAndView.model;

import com.bfox.xunbao.common.core.LimitModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色条件参数映射
 * @author eden
 * @date 2022/10/23 21:22:22
 */
@Getter
@Setter
public class RoleModel extends LimitModel {

	private static final long serialVersionUID = 1L;
	
	private String name;
}
