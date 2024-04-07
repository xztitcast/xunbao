package com.bfox.xunbao.admin.web.modelAndView.view;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 后台管理系统前后端交互行政地区实体类
 * 构建select选择器需要的参数
 * @author eden
 * @date 2023年2月18日 下午3:20:30
 */
@Getter
@Setter
public class TreeNodeView<T> extends TreeView<T> {

	private T parentId;
	
	/**
	 * 子节点
	 */
	private List<TreeNodeView> children;

	public TreeNodeView() {
		super();
	}

	public TreeNodeView(String label, T id, T parentId, List<TreeNodeView> children) {
		this(label, id, false, false, parentId, children);
	}

	public TreeNodeView(String label, T id, boolean isLeaf, T parentId, List<TreeNodeView> children) {
		this(label, id, isLeaf, false, parentId, children);
	}

	public TreeNodeView(String label, T id, boolean isLeaf, boolean disabled, T parentId, List<TreeNodeView> children) {
		super(label, id, isLeaf, disabled);
		this.parentId = parentId;
		this.children = children == null ? new ArrayList<>() : children;
	}
}
