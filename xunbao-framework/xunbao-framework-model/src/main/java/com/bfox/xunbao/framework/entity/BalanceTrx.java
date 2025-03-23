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
 * 保证金划扣表
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Getter
@Setter
@TableName("tb_balance_trx")
public class BalanceTrx extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单任务ID
     */
    private Long orderWorkId;

    /**
     * 订单标号
     */
    private String serialNumber;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 扣除保证金
     */
    private BigDecimal amount;

    /**
     * 状态1:待申诉 2:申诉成功 3:申诉失败
     */
    private Short status;

    /**
     * 申诉周期
     */
    private Integer cycle;

    /**
     * 申诉图片
     */
    private String url;

    /**
     * 申诉视屏
     */
    private String video;
}
