package com.bfox.xunbao.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.sso.entity.Tenant;
import com.bfox.xunbao.sso.mapper.TenantMapper;
import com.bfox.xunbao.sso.i.service.TenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 机构信息 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2024-07-27 22:41:07
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements IService<Tenant>, TenantService {

    @Override
    public P<Tenant> getBaseList(BaseModel m) {
        return TenantService.super.getBaseList(m);
    }

    @Override
    public Tenant getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(Tenant t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(Tenant t) {
        return this.updateById(t);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return this.removeById(id);
    }

    @Override
    @Transactional
    public boolean delete(Collection<Long> ids) {
        return this.removeByIds(ids);
    }
}
