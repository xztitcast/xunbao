package com.bfox.xunbao.setup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.setup.entity.Content;
import com.bfox.xunbao.setup.mapper.ContentMapper;
import com.bfox.xunbao.setup.i.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 内容表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2024-04-02 23:41:38
 */
@Service
@DubboService(interfaceClass = ContentService.class)
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IService<Content>, ContentService {

    @Override
    public P<Content> getBaseList(LimitModel m) {
        IPage<Content> page = new Page<>(m.getPageNum(), m.getPageSize());
        QueryWrapper<Content> query = new QueryWrapper<>();
        query.orderBy(true, m.getOrderByAsc(), m.getOrderField());
        this.page(page, query);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public Content getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(Content t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(Content t) {
        return this.updateById(t);
    }

    @Override
    @Transactional
    public boolean delete(Collection<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return this.removeById(id);
    }
}
