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
 * 用户卡包表
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 14:23:28
 */
@Getter
@Setter
@TableName("tb_user_kabaw")
public class UserKabaw extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联活动订单号tb_activity_order
     */
    private Long activityOrderId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户头像
     */
    private String userPic;

    /**
     * 状态(1:待使用,2:已使用,3:已过期)
     */
    private Short status;

    /**
     * 奖品名称
     */
    private Long itemName;

    /**
     * 奖品图片
     */
    private String itemUrl;

    /**
     * 奖品有效期
     */
    private Integer itemExpire;

    /**
     * 奖品金额
     */
    private BigDecimal itemAmount;

}
