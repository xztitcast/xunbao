package com.bfox.xunbao.admin.web.entity;

import com.baomidou.mybatisplus.annotation.*;

import com.bfox.xunbao.common.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典实体类
 * @author eden
 * @date 2022/10/30 10:07:07
 */
@Getter
@Setter
@TableName("tb_sys_dict")
public class SysDict extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "key")
    private String key;

    @TableField(value = "value")
    private String value;

    private String remark;
}
