package com.bfox.xunbao.setup.service.controller;

import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.utils.BeanCopierUtil;
import com.bfox.xunbao.setup.entity.Article;
import com.bfox.xunbao.setup.i.service.ArticleCategoryService;
import com.bfox.xunbao.setup.i.service.ArticleService;
import com.bfox.xunbao.setup.model.ArticleAndClassroomModel;
import com.bfox.xunbao.setup.view.ArticleContentView;
import com.bfox.xunbao.setup.view.ArticleView;
import com.bfox.xunbao.setup.view.CategoryView;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章列表
 * @author eden
 * @date 2023年2月23日 下午4:38:28
 */
@RestController
@RequestMapping("/setup/article")
public class ArticleController {
	
	@DubboReference
	private ArticleService articleService;
	
	@DubboReference
	private ArticleCategoryService articleCategoryService;

	/**
	 * 获取文章列表
	 * @param acm
	 * @return
	 */
	@PostMapping("/list")
	public R list(@RequestBody ArticleAndClassroomModel acm) {
		P<ArticleView> p = articleService.getArticleList(acm);
		return R.ok(p);
	}
	
	/**
	 * 获取文章内容
	 * @param id
	 * @return
	 */
	@GetMapping("/content/{id}")
	public R cotent(@PathVariable Long id) {
		Article entity = articleService.getEntity(id);
		ArticleContentView acv = new ArticleContentView();
		if(entity != null) {
			BeanCopierUtil.copyProperties(entity, acv);
		}
		return R.ok(acv);
	}
	
	/**
	 * 获取文章分类
	 * @return
	 */
	@GetMapping("/category")
	public R category() {
		List<CategoryView> list = articleCategoryService.getArticleCategoryList().stream().map(a -> new CategoryView(a.getId(), a.getName())).collect(Collectors.toList());
		return R.ok(list);
	}
}
