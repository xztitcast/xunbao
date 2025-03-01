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
 * 订单任务表
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Getter
@Setter
@TableName("tb_order_work")
public class OrderWork extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 任务编号
     */
    private String serialNumber;

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
     * 状态0:无责取消 1:有责取消 2:进行中 3:待验收 4:已验收 5:已完成
     */
    private Short state;

    /**
     * 奖励(有可能多人同时进行一个订单)
     */
    private BigDecimal bonus;

    /**
     * 接单用户保证金
     */
    private BigDecimal bond;

    /**
     * 备注(取消时添加备注)
     */
    private String remark;

    /**
     * 证明(取消时添加证明)
     */
    private String proofUrl;
}
