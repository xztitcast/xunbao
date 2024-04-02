	package com.bfox.xunbao.setup.i.service;

import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.setup.entity.Article;
import com.bfox.xunbao.setup.model.ArticleAndClassroomModel;
import com.bfox.xunbao.setup.view.ArticleView;

/**
 * 文章Service业务接口
 * @author eden
 * @date 2023年2月22日 下午6:22:12
 */
public interface ArticleService extends FrameworkService<Article, Long> {

	/**
	 * 文章列表
	 * @param bm
	 * @return
	 */
	P<ArticleView> getArticleList(ArticleAndClassroomModel acm);
}
