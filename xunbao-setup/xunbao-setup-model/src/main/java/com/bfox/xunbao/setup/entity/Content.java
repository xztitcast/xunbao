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
 * 内容表
 * </p>
 *
 * @author Eden
 * @since 2024-04-02 23:41:38
 */
@Getter
@Setter
@TableName("tb_content")
public class Content extends CreateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容类目ID
     */
    private Long cid;

    /**
     * 内容1
     */
    private String text1;

    /**
     * 内容2
     */
    private String text2;

    /**
     * 内容3
     */
    private String text3;

    /**
     * 内容4
     */
    private String text4;

    /**
     * 内容5
     */
    private String text5;

    /**
     * 链接
     */
    private String url;

    /**
     * 图片绝对路径
     */
    private String pic;

    /**
     * 图片2
     */
    private String pic2;

    /**
     * 图片3
     */
    private String pic3;

}
