package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.ActivityItem;
import com.bfox.xunbao.framework.i.service.ActivityItemService;
import com.bfox.xunbao.framework.mapper.ActivityItemMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 活动奖励表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 14:23:28
 */
@Service
@DubboService(interfaceClass = ActivityItemService.class)
public class ActivityItemServiceImpl extends ServiceImpl<ActivityItemMapper, ActivityItem> implements IService<ActivityItem>, ActivityItemService {

    @Override
    public ActivityItem getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(ActivityItem t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(ActivityItem t) {
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
