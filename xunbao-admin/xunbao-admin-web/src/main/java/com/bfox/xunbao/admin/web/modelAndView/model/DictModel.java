package com.bfox.xunbao.admin.web.modelAndView.model;

import java.io.Serializable;

import com.bfox.xunbao.common.core.LimitModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 字典Form表单实体类
 * @author eden
 * @date 2022年10月30日 下午4:48:04
 */
@Getter
@Setter
public class DictModel extends LimitModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;
}
