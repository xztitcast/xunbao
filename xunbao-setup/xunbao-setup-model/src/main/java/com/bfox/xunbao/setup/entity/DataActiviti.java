package com.bfox.xunbao.setup.entity;

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
 * 数据审批流表
 * </p>
 *
 * @author Eden
 * @since 2024-09-08 18:49:54
 */
@Getter
@Setter
@TableName("tb_data_activiti")
public class DataActiviti extends CreateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级审批流ID
     */
    private Long parentId;

    /**
     * 审批流名称
     */
    private String name;

    /**
     * 数据主键ID
     */
    private Long dataId;

    /**
     * 1待审核 2审核中 3审核成功 4审核驳回
     */
    private Integer status;

    /**
     * 类型详情见JAVA代码activiti枚举
     */
    private Integer type;

    /**
     * 类型详情见JAVA代码activiti枚举
     */
    private String typeName;

    /**
     * 审核人ID
     */
    private Long auditId;

    /**
     * 审核人名称
     */
    private String auditName;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 审核驳回必须填写的备注信息
     */
    private String remark;
}
