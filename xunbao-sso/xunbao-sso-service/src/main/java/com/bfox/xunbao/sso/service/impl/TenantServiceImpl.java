package com.bfox.xunbao.sso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.Constant;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.sso.entity.Tenant;
import com.bfox.xunbao.sso.i.service.TenantService;
import com.bfox.xunbao.sso.mapper.TenantMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public P<Tenant> getBaseList(LimitModel m) {
        IPage<Tenant> page = new Page<>(m.getPageNum(), m.getPageSize());
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(true, m.getOrderByAsc(), m.getOrderField());
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
        boolean save = this.save(t);
        if(save) this.updateEntity(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(Tenant t) {
        Long parentId = t.getParentId();
        if(parentId == null || parentId == 0) {
            t.setPath(String.valueOf(t.getId()));
        }else {
            Tenant entity = this.getById(parentId);
            t.setPath(entity.getPath() + Constant.DELIMITER_SLASH + t.getId());
        }
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
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.lambdaQuery(Tenant.class).eq(Tenant::getParentId, id);
        List<Tenant> list = this.list(queryWrapper);
        list.add(entity);
        return list.stream().map(Tenant::getId).collect(Collectors.toList());
    }

    @Override
    public List<Tenant> getTenantList(String name) {
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.lambdaQuery(Tenant.class).orderByAsc(Tenant::getCreated);
        if(StringUtils.isNotBlank(name)) {
            Tenant entity = this.getOne(Wrappers.lambdaQuery(Tenant.class).eq(Tenant::getName, name));
            if(entity != null) {
                String[] split = entity.getPath().split(Constant.DELIMITER_SLASH);
                queryWrapper.likeRight(Tenant::getPath, split[0]);
            }
        }
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
