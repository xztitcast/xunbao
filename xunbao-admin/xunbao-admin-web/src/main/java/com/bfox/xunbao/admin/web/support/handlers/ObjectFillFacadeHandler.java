package com.bfox.xunbao.admin.web.support.handlers;

import com.bfox.xunbao.admin.web.annotation.FillType;
import com.bfox.xunbao.admin.web.entity.SysUser;
import com.bfox.xunbao.admin.web.support.SecuritySubjectManager;
import com.bfox.xunbao.common.mybatis.entity.CreateEntity;
import com.bfox.xunbao.common.mybatis.entity.TenantEntity;

import java.util.Date;

/**
 * 对象填充器外观器
 * @author eden
 * @date 2024/8/13 21:45:45
 */
public final class ObjectFillFacadeHandler {


    /**
     * 填充
     * @param arg
     * @param value
     */
    public static void doFill(Object arg, FillType value) {
        if(arg instanceof TenantEntity && value == FillType.INSERT) {
            tenantFillHandler.doFill((TenantEntity) arg);
        }
        if(arg instanceof CreateEntity && value == FillType.INSERT) {
            createFillHandler.doFill((CreateEntity) arg);
        }
        if(arg instanceof CreateEntity && value  == FillType.UPDATE) {
            updateFillHandler.doFill((CreateEntity) arg);
        }
    }


    /**
     * 租户填充器
     */
    private static ObjectFillHandler<TenantEntity> tenantFillHandler = (object) -> {
        SysUser subject = SecuritySubjectManager.getSubject();
        object.setTenantId(subject.getTenantId());
        object.setTenantName(subject.getTenantName());
    };

    /**
     * 创建人填充器
     */
    private static ObjectFillHandler<CreateEntity> createFillHandler = (object) -> {
        SysUser subject = SecuritySubjectManager.getSubject();
        object.setCreator(subject.getId());
        object.setCreateName(subject.getUsername());
        object.setCreated(new Date());
        object.setUpdater(subject.getId());
        object.setUpdateName(subject.getUsername());
        object.setUpdated(new Date());
    };

    /**
     * 更新人填充器
     */
    private static ObjectFillHandler<CreateEntity> updateFillHandler = (object) -> {
        SysUser subject = SecuritySubjectManager.getSubject();
        object.setUpdater(subject.getId());
        object.setUpdateName(subject.getUsername());
        object.setUpdated(new Date());
    };
}
