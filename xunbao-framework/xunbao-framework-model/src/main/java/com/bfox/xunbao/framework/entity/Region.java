package com.bfox.xunbao.framework.entity;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 全国行政省市区信息表
 * </p>
 *
 * @author Eden
 * @since 2024-08-01 22:48:47
 */
@Getter
@Setter
@TableName("tb_region")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 省份、城市、区县编号
     */
    private String cid;

    /**
     * 省份、城市、区县名称
     */
    private String cname;

    /**
     * 父级省份、城市、区县编号
     */
    private String pid;

    /**
     * 父级省份、城市、区县名称
     */
    private String pname;

    /**
     * 0无效 1有效
     */
    private Short status;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 创建时间
     */
    private LocalDateTime updated;

    /**
     * 级别(省-1级、市-2级、区/县-3级)
     */
    private Short level;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
