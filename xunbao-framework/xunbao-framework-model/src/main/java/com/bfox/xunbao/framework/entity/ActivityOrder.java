package com.bfox.xunbao.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 活动订单表
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 14:23:28
 */
@Getter
@Setter
@TableName("tb_activity_order")
public class ActivityOrder extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 关联订单号
     */
    private String assOrderId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 奖品ID
     */
    private Long itemId;

    /**
     * 奖品名称
     */
    private Long itemName;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户头像
     */
    private String userPic;

    /**
     * 状态(1:待支付,2:已支付,3:已取消,4:支付失败)
     */
    private Short status;

}
