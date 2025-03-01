package com.bfox.xunbao.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 活动周期规则表
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Getter
@Setter
@TableName("tb_activity_cycle_rule")
public class ActivityCycleRule extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id(即活动ID)
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 每月参与次数
     */
    private Short month;

    /**
     * 每周参与次数
     */
    private Short week;

    /**
     * 每天参与次数
     */
    private Short day;
}
