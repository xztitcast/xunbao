package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.annotation.RuleType;
import com.bfox.xunbao.framework.entity.ActivityRule;
import com.bfox.xunbao.framework.i.service.ActivityRuleService;
import com.bfox.xunbao.framework.i.service.support.ActivityRuleExtendService;
import com.bfox.xunbao.framework.mapper.ActivityRuleMapper;
import com.bfox.xunbao.framework.view.RuleView;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
public class ActivityRuleServiceImpl extends ServiceImpl<ActivityRuleMapper, ActivityRule> implements IService<ActivityRule>, ActivityRuleService, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext applicationContext;

    private final List<String> beanNames = new ArrayList<>();

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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        if (Objects.requireNonNull(applicationContext.getParent()).getParent() == null) {
            String[] names = applicationContext.getBeanNamesForAnnotation(RuleType.class);
            this.beanNames.addAll(Arrays.asList(names));
        }
        this.applicationContext = applicationContext;
    }

    @Override
    public List<RuleView> getDynamicSelection() {
        return beanNames.stream().map(beanName -> {
            ActivityRuleExtendService bean = this.applicationContext.getBean(beanName, ActivityRuleExtendService.class);
            return bean.extension();
        }).toList();
    }
}
