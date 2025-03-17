package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.framework.entity.Star;
import com.bfox.xunbao.framework.i.service.StarService;
import com.bfox.xunbao.framework.mapper.StarMapper;
import com.bfox.xunbao.framework.model.SysCommonModel;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 星级表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-04 01:28:33
 */
@Service
@DubboService(interfaceClass = StarService.class)
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements IService<Star>, StarService {
    @Override
    public P<Star> getBaseList(LimitModel m) {
        SysCommonModel model = (SysCommonModel) m;
        IPage<Star> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<Star> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(model.getName()), "name", model.getName());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public Star getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(Star t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(Star t) {
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

    @Override
    public List<Star> getSelection() {
        return this.list();
    }
}
