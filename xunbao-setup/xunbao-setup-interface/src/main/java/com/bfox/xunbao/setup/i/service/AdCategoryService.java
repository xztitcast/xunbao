package com.bfox.xunbao.setup.i.service;

import java.util.List;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.setup.entity.AdCategory;

/**
 * 广告类目Service业务接口
 * @author eden
 * @date 2023年2月11日 下午4:25:40
 */
public interface AdCategoryService extends FrameworkService<AdCategory, Integer> {

	/**
	 * 获取类目列表
	 * @return
	 */
	List<AdCategory> getAdCategoryList();
}
