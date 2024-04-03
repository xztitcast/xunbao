package com.bfox.xunbao.admin.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.bfox.xunbao.common.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@TableName(value = "tb_sys_role")
public class SysRole extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String remark;

    private Long creator;

    @TableField(exist = false)
    private List<Long> menuIdList;

}