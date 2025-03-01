package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.framework.entity.ActivityCycleRule;
import com.bfox.xunbao.framework.i.service.ActivityCycleRuleService;
import com.bfox.xunbao.framework.mapper.ActivityCycleRuleMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 * 活动周期规则表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
public class ActivityCycleRuleServiceImpl extends ServiceImpl<ActivityCycleRuleMapper, ActivityCycleRule> implements IService<ActivityCycleRule>, ActivityCycleRuleService {
    @Override
    public P<ActivityCycleRule> getBaseList(LimitModel m) {
        return ActivityCycleRuleService.super.getBaseList(m);
    }

    @Override
    public ActivityCycleRule getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    public Long saveEntity(ActivityCycleRule t) {
        this.save(t);
        return t.getId();
    }

    @Override
    public boolean updateEntity(ActivityCycleRule t) {
        return this.updateById(t);
    }

    @Override
    public boolean delete(Collection<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean delete(Long id) {
        return this.removeById(id);
    }
}
