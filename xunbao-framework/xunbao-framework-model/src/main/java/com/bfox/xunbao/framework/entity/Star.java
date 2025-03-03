package com.bfox.xunbao.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.CreateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 星级表
 * </p>
 *
 * @author Eden
 * @since 2025-03-04 01:28:33
 */
@Getter
@Setter
@TableName("tb_star")
public class Star extends CreateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 星级名称 武林新秀、小有名气、渐入佳境、名动一方、武林豪侠、一代宗师、绝世高手
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 起始值
     */
    private Integer startValue;

    /**
     * 结束值
     */
    private Integer endValue;

}
