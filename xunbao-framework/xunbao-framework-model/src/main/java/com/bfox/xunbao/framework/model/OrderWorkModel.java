package com.bfox.xunbao.framework.model;


import com.bfox.xunbao.common.core.LimitModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 后台订单工作流查询对象
 * @Author Eden
 * @Date 2025/3/1 17:20
 */
@Getter
@Setter
public class OrderWorkModel extends LimitModel {

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 后台管理用户ID
     */
    private Long sysUserId;
}
