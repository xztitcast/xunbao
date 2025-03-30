package com.bfox.xunbao.framework.model;


import com.bfox.xunbao.common.core.LimitModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 后台管理分页查询参数
 * @Author Eden
 * @Date 2025/3/5 0:34
 */
@Getter
@Setter
public class CommonModel extends LimitModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
}
