package com.bfox.xunbao.framework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.CreateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 任务订单表
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Getter
@Setter
@TableName("tb_order")
public class Order extends CreateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 周期
     */
    private Integer cycle;

    /**
     * 后台状态0:审核失败 1:待审核 2:审核成功 3:待发布 4:已发布 5:已结束
     */
    private Integer status;

    /**
     * 类型(1:需求 2:BUG)
     */
    private Integer type;

    /**
     * 奖励
     */
    private BigDecimal bonus;

    /**
     * 保证金(保证金最大是预算的双倍)
     */
    private BigDecimal bond;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 联系方式(微信二维码)
     */
    private String contact;

    /**
     * 图片
     */
    private String url;

    /**
     * 开发语言
     */
    private String develop;

    /**
     * 文案
     */
    private String description;

}
