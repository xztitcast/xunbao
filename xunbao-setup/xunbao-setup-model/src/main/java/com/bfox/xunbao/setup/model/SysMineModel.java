package com.bfox.xunbao.setup.model;

import java.io.Serial;

import com.bfox.xunbao.common.core.LimitModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 后台管理系统 查询"我的页面"table列表数据的查询条件
 * smart-admin-web -> SysMineController -> list()
 * @author eden
 * @date 2023年3月2日 下午8:34:43
 */
@Getter
@Setter
public class SysMineModel extends LimitModel {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private Long tisid;

}
