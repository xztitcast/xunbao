package com.bfox.xunbao.admin.web.modelAndView.model;

import com.bfox.xunbao.common.core.LimitModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统用户条件参数映射
 * @author eden
 * @date 2022/10/23 21:21:21
 */
@Getter
@Setter
public class UserModel extends LimitModel {
	
	private static final long serialVersionUID = 1L;

	private String username;
}
