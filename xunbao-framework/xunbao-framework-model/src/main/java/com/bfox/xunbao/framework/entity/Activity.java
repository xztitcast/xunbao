package com.bfox.xunbao.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.CreateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Getter
@Setter
@TableName("tb_activity")
public class Activity extends CreateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动图片地址
     */
    private String url;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 剩余库存
     */
    private Integer surplus;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 状态0:暂停 1:下架 2:上架
     */
    private Short status;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 更新人
     */
    private Long updater;

    /**
     * 创建名称
     */
    private String createName;

    /**
     * 更新人名称
     */
    private String updateName;
}
