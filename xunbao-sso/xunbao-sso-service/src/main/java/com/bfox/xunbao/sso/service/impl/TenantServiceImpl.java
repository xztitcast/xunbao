package com.bfox.xunbao.sso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.sso.entity.Tenant;
import com.bfox.xunbao.sso.mapper.TenantMapper;
import com.bfox.xunbao.sso.i.service.TenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 机构信息 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2024-07-27 22:41:07
 */
@Service
@DubboService(interfaceClass = TenantService.class)
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements IService<Tenant>, TenantService {

    @Override
    public P<Tenant> getBaseList(BaseModel m) {
        IPage<Tenant> page = new Page<>(m.getPageNum(), m.getPageSize());
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(true, m.getOrder(), m.getOrderField());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
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

    @Override
    public List<Long> getTenantIdList(long id) {
        Tenant entity = this.getById(id);
        long parentId = entity.getParentId() == 0 ? id : entity.getParentId();
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.lambdaQuery(Tenant.class).eq(Tenant::getParentId, parentId);
        List<Tenant> list = this.list(queryWrapper);
        return CollectionUtils.isEmpty(list) ? List.of(id) : list.stream().map(Tenant::getId).collect(Collectors.toList());
    }

    @Override
    public List<Tenant> getTenantList(String name) {
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.lambdaQuery(Tenant.class).eq(StringUtils.isBlank(name), Tenant::getName, name).orderByAsc(Tenant::getCreated);
        List<Tenant> list = this.list(queryWrapper);
        return list.stream().filter(e -> e.getParentId() == 0L).map(e -> findTreeNode(list, e)).collect(Collectors.toList());
    }

    /**
     * 查找属性结点
     * @param tenantList
     * @param tenant
     * @return
     */
    private Tenant findTreeNode(final List<Tenant> tenantList, final Tenant tenant) {
        tenantList.stream().filter(e -> e.getParentId() == tenant.getId().longValue()).forEach(e -> tenant.getChildren().add(findTreeNode(tenantList, e)));
        return tenant;
    }

}