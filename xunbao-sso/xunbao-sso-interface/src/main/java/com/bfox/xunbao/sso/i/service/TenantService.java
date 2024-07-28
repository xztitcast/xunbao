package com.bfox.xunbao.sso.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.sso.entity.Tenant;

import java.util.List;

/**
 * <p>
 * 机构信息 服务类
 * </p>
 *
 * @author Eden
 * @since 2024-07-27 22:41:07
 */
public interface TenantService extends FrameworkService<Tenant, Long> {

    /**
     * 获取父子机构ID列表
     * @param id 机构ID
     * @return
     */
    List<Long> getTenantIdList(long id);

    /**
     * 获取所有机构列表
     * @param name 机构名称
     * @return
     */
    List<Tenant> getTenantList(String name);
}
