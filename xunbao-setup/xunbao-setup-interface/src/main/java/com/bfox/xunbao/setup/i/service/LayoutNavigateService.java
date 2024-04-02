package com.bfox.xunbao.setup.i.service;

import java.util.List;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.setup.entity.LayoutNavigate;

/**
 * 小程序导航ICON业务接口
 * @author eden
 * @date 2023年2月10日 下午4:04:13
 */
public interface LayoutNavigateService extends FrameworkService<LayoutNavigate, Long> {
	
	/**
	 * 获取导航栏列表数据
	 * @param layoutId 布局ID
	 * @return
	 */
	List<LayoutNavigate> getLayoutNavigateList(Integer layoutId);

}
