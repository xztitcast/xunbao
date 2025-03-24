package com.bfox.xunbao.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bfox.xunbao.common.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author Eden
 * @since 2025-03-24 23:01:11
 */
@Getter
@Setter
@TableName("tb_user_info")
public class UserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String accName;

    /**
     * 账号
     */
    private String accNo;

    /**
     * 收款码
     */
    private String accCode;

    /**
     * 1:微信 2:支付宝 3:银行卡
     */
    private Short type;
}
