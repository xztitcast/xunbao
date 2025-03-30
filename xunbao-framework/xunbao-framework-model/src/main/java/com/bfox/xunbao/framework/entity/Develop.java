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
 * 开发语言表
 * </p>
 *
 * @author Eden
 * @since 2025-03-30 18:38:07
 */
@Getter
@Setter
@TableName("tb_develop")
public class Develop extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开发名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sorted;
}
