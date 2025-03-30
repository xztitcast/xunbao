package com.bfox.xunbao.framework.model;


import com.bfox.xunbao.common.core.LimitModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author Eden
 * @Date 2025/3/5 0:26
 */
@Getter
@Setter
public class BalanceModel extends LimitModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;
}
