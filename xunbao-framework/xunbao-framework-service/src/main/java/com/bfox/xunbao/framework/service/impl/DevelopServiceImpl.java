package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.Develop;
import com.bfox.xunbao.framework.i.service.DevelopService;
import com.bfox.xunbao.framework.mapper.DevelopMapper;
import com.bfox.xunbao.framework.view.DevelopView;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 开发语言表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-30 18:38:07
 */
@Service
@DubboService(interfaceClass = DevelopService.class)
public class DevelopServiceImpl extends ServiceImpl<DevelopMapper, Develop> implements IService<Develop>, DevelopService {

    @Override
    public List<DevelopView> getDataList() {
        LambdaQueryWrapper<Develop> queryWrapper = Wrappers.lambdaQuery(Develop.class).orderByAsc(Develop::getSorted);
        return this.list(queryWrapper).stream().map(DevelopView::new).toList();
    }

    @Override
    public Develop getEntity(Integer id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Integer saveEntity(Develop t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(Develop t) {
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
}
