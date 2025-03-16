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
 * 活动用户规则表
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Getter
@Setter
@TableName("tb_user_rule")
public class UserRule extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id(即活动ID)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 新用户规则名称
     */
    private String name;

    /**
     * 新用户注册多少天内
     */
    private Integer day;

    public UserRule() {

    }

    public UserRule(Long id, Integer day) {
        this.id = id;
        this.day = day;
    }
}
