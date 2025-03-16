package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.ActivityRule;
import com.bfox.xunbao.framework.i.service.ActivityRuleService;
import com.bfox.xunbao.framework.mapper.ActivityRuleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 活动规则关联表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 15:47:09
 */
@Service
@DubboService(interfaceClass = ActivityRuleService.class)
public class ActivityRuleServiceImpl extends ServiceImpl<ActivityRuleMapper, ActivityRule> implements IService<ActivityRule>, ActivityRuleService {

    @Override
    public ActivityRule getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(ActivityRule t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(ActivityRule t) {
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
