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
 * 标签表
 * </p>
 *
 * @author Eden
 * @since 2025-03-04 01:28:33
 */
@Getter
@Setter
@TableName("tb_label")
public class Label extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * Spring bean名称
     */
    private String beanName;
}
