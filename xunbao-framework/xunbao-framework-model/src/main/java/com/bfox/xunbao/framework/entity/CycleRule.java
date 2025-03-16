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
@TableName("tb_cycle_rule")
public class CycleRule extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id(即活动ID)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 周期规则名称
     */
    private String name;

    /**
     * 每月参与次数
     */
    private Integer month;

    /**
     * 每周参与次数
     */
    private Integer week;

    /**
     * 每天参与次数
     */
    private Integer day;

    public CycleRule() {

    }

    public CycleRule(Long id, Integer month, Integer week, Integer day) {
        this.id = id;
        this.month = month;
        this.week = week;
        this.day = day;
    }
}
