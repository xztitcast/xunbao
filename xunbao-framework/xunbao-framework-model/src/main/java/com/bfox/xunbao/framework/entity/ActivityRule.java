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
 * 活动规则关联表
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 15:47:09
 */
@Getter
@Setter
@TableName("tb_activity_rule")
public class ActivityRule extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 规则ID
     */
    private Long ruleId;

    /**
     * 规则类别(1:新用户规则2:周期规则3:星级规则)
     */
    private Integer ruleType;

}
