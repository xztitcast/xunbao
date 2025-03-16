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
 * 活动星级规则表
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Getter
@Setter
@TableName("tb_star_rule")
public class StarRule extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id(即活动ID)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 星级规则名称
     */
    private String name;

    /**
     * 星级级别(字符串数组存储)
     */
    private String starIds;

    /**
     * 星级名称(前端显示使用)
     */
    private String starNames;

    public StarRule() {

    }

    public StarRule(Long id, String starIds) {
        this.id = id;
        this.starIds = starIds;
    }
}
