package com.bfox.xunbao.setup.i.service;

import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.setup.entity.Classroom;
import com.bfox.xunbao.setup.model.ArticleAndClassroomModel;
import com.bfox.xunbao.setup.view.ClassroomView;

/**
 * 数币课堂Service业务接口
 * @author eden
 * @date 2023年2月23日 下午7:34:03
 */
public interface ClassroomService extends FrameworkService<Classroom, Long> {

	/**
	 * 课堂列表
	 * @param bm
	 * @return
	 */
	P<ClassroomView> getClassroomList(ArticleAndClassroomModel bm);
}
