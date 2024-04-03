package com.bfox.xunbao.admin.web.entity;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 数据字典实体类
 * @author eden
 * @date 2022/10/30 10:07:07
 */
@Getter
@Setter
@TableName("tb_sys_dict")
public class SysDict {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "key")
    private String key;

    @TableField(value = "value")
    private String value;

    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date created;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;
}
