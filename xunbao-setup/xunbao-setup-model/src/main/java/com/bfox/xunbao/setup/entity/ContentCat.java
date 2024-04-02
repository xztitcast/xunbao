package com.bfox.xunbao.setup.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.bfox.xunbao.common.mybatis.entity.CreateEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 内容分类表
 * </p>
 *
 * @author Eden
 * @since 2024-04-02 23:41:38
 */
@Getter
@Setter
@TableName("tb_content_cat")
public class ContentCat extends CreateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 状态是否删除 0:否 1:是
     */
    private Short status;

    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列
     */
    private Short sorted;

    /**
     * 所属类目，叶子类目
     */
    private String path;

    /**
     * 终端PC:浏览器, APP:手机应用端, MIN:微信小程序
     */
    private String terminal;

}
