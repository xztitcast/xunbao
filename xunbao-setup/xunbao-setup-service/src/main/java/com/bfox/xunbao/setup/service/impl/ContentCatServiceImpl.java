package com.bfox.xunbao.setup.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.setup.entity.ContentCat;
import com.bfox.xunbao.setup.mapper.ContentCatMapper;
import com.bfox.xunbao.setup.i.service.ContentCatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 内容分类表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2024-04-02 23:41:38
 */
@Service
@DubboService(interfaceClass = ContentCatService.class)
public class ContentCatServiceImpl extends ServiceImpl<ContentCatMapper, ContentCat> implements IService<ContentCat>, ContentCatService {

    @Override
    public P<ContentCat> getBaseList(BaseModel m) {
        return ContentCatService.super.getBaseList(m);
    }

    @Override
    public ContentCat getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public ContentCat saveEntity(ContentCat t) {
        this.save(t);
        return t;
    }

    @Override
    @Transactional
    public boolean updateEntity(ContentCat t) {
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
