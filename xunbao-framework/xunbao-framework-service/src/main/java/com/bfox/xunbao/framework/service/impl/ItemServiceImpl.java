package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.framework.entity.Item;
import com.bfox.xunbao.framework.i.service.ItemService;
import com.bfox.xunbao.framework.mapper.ItemMapper;
import com.bfox.xunbao.framework.model.SysCommonModel;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 奖励表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
@DubboService(interfaceClass = ItemService.class)
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IService<Item>, ItemService {

    @Override
    public P<Item> getBaseList(LimitModel m) {
        SysCommonModel model = (SysCommonModel) m;
        IPage<Item> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(model.getName()), "name", model.getName());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public Item getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(Item t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(Item t) {
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
