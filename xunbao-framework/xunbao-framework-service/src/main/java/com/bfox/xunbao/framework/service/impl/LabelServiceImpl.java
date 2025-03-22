package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.framework.entity.Label;
import com.bfox.xunbao.framework.i.service.LabelService;
import com.bfox.xunbao.framework.mapper.LabelMapper;
import com.bfox.xunbao.framework.model.SysCommonModel;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-04 01:28:33
 */
@Service
@DubboService(interfaceClass = LabelService.class)
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements IService<Label>, LabelService {

    @Override
    public P<Label> getBaseList(LimitModel m) {
        SysCommonModel model = (SysCommonModel) m;
        IPage<Label> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(model.getName()), "name", model.getName());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public Label getEntity(Integer id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Integer saveEntity(Label t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(Label t) {
        return this.updateById(t);
    }

    @Override
    @Transactional
    public boolean delete(Collection<Integer> ids) {
        return this.removeByIds(ids);
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return this.removeById(id);
    }

    @Override
    public List<Label> getSelection() {
        return this.list();
    }
}
