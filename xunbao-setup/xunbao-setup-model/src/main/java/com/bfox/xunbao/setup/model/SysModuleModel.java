package com.bfox.xunbao.setup.model;

import com.bfox.xunbao.common.core.LimitModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 后台管理系统 查询导航库table列表数据的查询条件
 * smart-admin-web -> SysModuleController -> list()
 * @author eden
 * @date 2023年3月13日 下午6:34:23
 */
@Getter
@Setter
public class SysModuleModel extends LimitModel {
	
	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 模块名
	 */
	private String name;
}
