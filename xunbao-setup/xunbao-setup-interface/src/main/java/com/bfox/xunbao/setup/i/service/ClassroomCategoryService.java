package com.bfox.xunbao.setup.i.service;

import java.util.List;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.setup.entity.ClassroomCategory;

/**
 * 数币课堂类目Service接口
 * @author eden
 * @date 2023年2月23日 下午7:34:44
 */
public interface ClassroomCategoryService extends FrameworkService<ClassroomCategory, Integer> {

	/**
	 * 获取数币课堂类目列表
	 * @return
	 */
	List<ClassroomCategory> getArticleCategoryList();
}
