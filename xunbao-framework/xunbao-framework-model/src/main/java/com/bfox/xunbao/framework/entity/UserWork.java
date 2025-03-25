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
 * 用户工作台
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Getter
@Setter
@TableName("tb_user_work")
public class UserWork extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id(即用户ID)
     */
    @TableId(type = IdType.INPUT)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 分成比例
     */
    private BigDecimal rate;

    /**
     * 交易量
     */
    private BigDecimal tradeAmount;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 信用分
     */
    private Integer num0;

    /**
     * 进行中统计数
     */
    private Integer num1;

    /**
     * 待验收统计数
     */
    private Integer num2;

    /**
     * 已验收统计数
     */
    private Integer num3;

    /**
     * 已完成统计数
     */
    private Integer num4;

    /**
     * 有责取消统计数
     */
    private Integer num5;

    /**
     * 无责取消统计数
     */
    private Integer num6;

    /**
     * 销售源码统计数
     */
    private Integer num7;

    /**
     *
     */
    private Integer num8;

}
