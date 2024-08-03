package com.bfox.xunbao.admin.web.modelAndView.view;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理系统前后端交互行政地区实体类
 * 构建select选择器需要的参数
 * @author eden
 * @date 2023年2月18日 下午3:20:30
 */
@Getter
@Setter
public class TreeNodeView<T> extends SelectorView<T> {

	private T parentId;

	/**
	 * 子节点
	 */
	private List<TreeNodeView> children = new ArrayList<>();

	public TreeNodeView() {
		super();
	}

	public TreeNodeView(String label, T id, T parentId) {
		this(label, id, false, parentId);
	}

	public TreeNodeView(String label, T id, boolean disabled, T parentId) {
		super(label, id, disabled);
		this.parentId = parentId;
	}
}
