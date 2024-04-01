package com.bfox.xunbao.common.core;

import com.bfox.xunbao.common.core.serialize.IdKeySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装视图ID
 * 防止遍历
 * @author eden
 * @date 2023年4月6日 下午6:42:07
 */
@Getter
@Setter
public class ModelAndView<T> {

	@JsonSerialize(using = IdKeySerializer.class)
	protected T id;
}
