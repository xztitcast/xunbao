package com.bfox.xunbao.sso.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.bfox.xunbao.common.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 机构信息
 * </p>
 *
 * @author Eden
 * @since 2024-07-27 22:41:07
 */
@Getter
@Setter
@TableName("tb_tenant")
public class Tenant extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构全名
     */
    private String name;

    /**
     * 父机构ID,0为总机构
     */
    private Long parentId;

    /**
     * 父机构名称
     */
    private String parentName;

    /**
     * 状态是否有效0:无效 1:有效
     */
    private Short status;

    /**
     * 机构服务电话(类似400)
     */
    private String phones;

    /**
     * 负责人姓名
     */
    private String tname;

    /**
     * 负责人电话
     */
    private String tphone;

    /**
     * 省编码
     */
    private String pid;

    /**
     * 省名称
     */
    private String pname;

    /**
     * 市编码
     */
    private String cid;

    /**
     * 市名称
     */
    private String cname;

    /**
     * 区编码
     */
    private String aid;

    /**
     * 区名称
     */
    private String aname;

    /**
     * 机构详细地址
     */
    private String address;

    /**
     * 签约时间
     */
    private LocalDateTime signtime;

    /**
     * 到期时间
     */
    private LocalDateTime expiretime;

    /**
     * 0-总机构 1-子机构
     */
    private String path;

    /**
     * 机构简称
     */
    private String remark;

}
