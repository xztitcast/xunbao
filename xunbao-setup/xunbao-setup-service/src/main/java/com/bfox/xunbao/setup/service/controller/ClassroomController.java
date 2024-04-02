package com.bfox.xunbao.setup.service.controller;

import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.utils.BeanCopierUtil;
import com.bfox.xunbao.setup.entity.Classroom;
import com.bfox.xunbao.setup.i.service.ClassroomCategoryService;
import com.bfox.xunbao.setup.i.service.ClassroomService;
import com.bfox.xunbao.setup.model.ArticleAndClassroomModel;
import com.bfox.xunbao.setup.view.CategoryView;
import com.bfox.xunbao.setup.view.ClassroomContentView;
import com.bfox.xunbao.setup.view.ClassroomView;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前端交互 数币课堂前端控制器
 * @author eden
 * @date 2023年2月23日 下午9:18:59
 */
@RestController
@RequestMapping("/setup/classroom")
public class ClassroomController {

	@DubboReference
	private ClassroomService classroomService;
	
	@DubboReference
	private ClassroomCategoryService classroomCategoryService;

	/**
	 * 获取课堂列表
	 * @param aacm
	 * @return
	 */
	@PostMapping("/list")
	public R list(@RequestBody ArticleAndClassroomModel aacm) {
		P<ClassroomView> p = classroomService.getClassroomList(aacm);
		return R.ok(p);
	}
	
	/**
	 * 获取文章内容
	 * @param id
	 * @return
	 */
	@GetMapping("/content/{id}")
	public R cotent(@PathVariable Long id) {
		Classroom entity = classroomService.getEntity(id);
		ClassroomContentView ccv = new ClassroomContentView();
		if(entity != null) {
			BeanCopierUtil.copyProperties(entity, ccv);
		}
		return R.ok(ccv);
	}
	
	/**
	 * 获取文章分类
	 * @return
	 */
	@GetMapping("/category")
	public R category() {
		List<CategoryView> list = classroomCategoryService.getArticleCategoryList().stream().map(a -> new CategoryView(a.getId(), a.getName())).collect(Collectors.toList());
		return R.ok(list);
	}
}
