package com.bfox.xunbao.framework.model;


import com.bfox.xunbao.common.core.LimitModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 后台订单申诉参数
 * @Author Eden
 * @Date 2025/3/23 23:30
 */
@Getter
@Setter
public class SysBalanceTrxModel extends LimitModel implements Serializable {

    /**
     * 订单任务ID
     */
    private Long orderWorkId;

    /**
     * 订单标号
     */
    private String serialNumber;

}
