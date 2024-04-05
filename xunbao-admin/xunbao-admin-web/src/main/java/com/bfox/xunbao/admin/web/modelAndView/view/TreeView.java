package com.bfox.xunbao.admin.web.modelAndView.view;

import lombok.Getter;
import lombok.Setter;

/**
 * 后台管理系统 选择器视图数据
 * @author eden
 * @date 2023年3月1日 下午7:29:09
 */
@Getter
@Setter
public class TreeView<T> {

	/**
	 * 选择器label值
	 */
	protected String label;
	
	/**
	 * 选择器label -> value 值
	 */
	protected T id;

	/**
	 * 是否叶子节点
	 */
	protected boolean isLeaf = false;
	
	/**
	 * 是否禁用
	 */
	protected boolean disabled = false;
	
	public TreeView() {
		
	}
	
	public TreeView(String label, T id) {
		this(label, id, false);
	}

	public TreeView(String label, T id, boolean isLeaf) {
		this(label, id, isLeaf, false);
	}

	public TreeView(String label, T id, boolean isLeaf, boolean disabled) {
		this.label = label;
		this.id = id;
		this.isLeaf = isLeaf;
		this.disabled = disabled;
	}
}
