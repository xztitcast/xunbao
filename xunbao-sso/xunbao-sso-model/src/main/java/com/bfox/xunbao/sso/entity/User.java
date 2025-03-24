package com.bfox.xunbao.sso.entity;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Eden
 * @since 2025-03-24 23:01:11
 */
@Getter
@Setter
@TableName("tb_user")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 微信union_id
     */
    private String unionId;

    /**
     * 小程序openid
     */
    private String openid;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 类型1:真实用户 2:虚拟用户
     */
    private Integer type;

    /**
     * IP地址
     */
    private String ip;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
