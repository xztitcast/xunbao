package com.bfox.xunbao.setup.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.setup.entity.Layout;

/**
 * 小程序导航全局配置
 * @author eden
 * @date 2023年2月10日 下午2:30:17
 */
public interface LayoutService extends FrameworkService<Layout, Integer> {
	
	/**
	 * 获取layout布局
	 * @param tisid
	 * @param type
	 * @return
	 */
	Layout getLayout(Long tisid, Integer type);
	
}
