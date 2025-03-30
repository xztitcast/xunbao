package com.bfox.xunbao.common.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * @author eden
 * 2022年5月11日
 * @param <T>
 */
@Getter
@Setter
public class P<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 总条数
	 */
	private long total;

	/**
	 * 总页数
	 */
	private long pages;

	/**
	 * 当前页数据
	 */
	@JsonInclude
	private List<T> pageList;

	public P() {
		super();
	}

	public P(long total, List<T> pageList) {
		this(total, 0, pageList);
	}

	public P(long total, long pages, List<T> pageList) {
		super();
		this.total = total;
		this.pages = pages;
		this.pageList = pageList;
	}
	
}
