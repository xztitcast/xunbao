package com.bfox.xunbao.framework.model;


import com.bfox.xunbao.common.core.LimitModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author Eden
 * @Date 2025/3/1 15:28
 */
@Getter
@Setter
public class OrderModel extends LimitModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 开发语言ID
     */
    private Integer developId;

    /**
     * 创建人
     */
    private Long creator;
}
