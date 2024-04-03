package com.bfox.xunbao.admin.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户租户关联表
 * </p>
 *
 * @author Eden
 * @since 2024-04-03 11:44:31
 */
@Getter
@Setter
@TableName("tb_sys_user_tenant")
public class SysUserTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID（即用户id）
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;
}
