package com.bfox.xunbao.common.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础数据结构
 * @author eden
 * @date 2024/8/13 22:07:07
 */
@Getter
@Setter
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理系统用户ID
     */
    private Long sysUserId;

    /**
     * 管理系统用户名称
     */
    private String sysUsername;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 租户名称
     */
    private String tenantName;


}
