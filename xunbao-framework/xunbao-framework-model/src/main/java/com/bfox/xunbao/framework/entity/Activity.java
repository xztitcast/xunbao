package com.bfox.xunbao.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.CreateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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
     * 活动背景图
     */
    private String backgroundImage;

    /**
     * 分享海报
     */
    private String posterImage;

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
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 状态0:暂停 1:下架 2:上架
     */
    private Integer status;

    /**
     * 活动描述
     */
    private String description;
}
