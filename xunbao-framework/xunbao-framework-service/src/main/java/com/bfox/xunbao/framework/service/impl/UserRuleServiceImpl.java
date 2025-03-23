package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.enums.BaseEnum;
import com.bfox.xunbao.framework.annotation.RuleType;
import com.bfox.xunbao.framework.entity.UserRule;
import com.bfox.xunbao.framework.i.service.UserRuleService;
import com.bfox.xunbao.framework.mapper.UserRuleMapper;
import com.bfox.xunbao.framework.model.SysCommonModel;
import com.bfox.xunbao.framework.view.RuleView;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 活动用户规则表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
@RuleType(BaseEnum.ONE)
@DubboService(interfaceClass = UserRuleService.class)
public class UserRuleServiceImpl extends ServiceImpl<UserRuleMapper, UserRule> implements IService<UserRule>, UserRuleService {

    private final String field = "userRule";

    private final String ruleName = "新用户规则";

    @Override
    public P<UserRule> getBaseList(LimitModel m) {
        SysCommonModel model = (SysCommonModel) m;
        IPage<UserRule> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<UserRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(model.getName()), "name", model.getName());
        queryWrapper.orderBy(true, model.getOrderByAsc(), model.getOrderField());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional
    public UserRule getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(UserRule t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(UserRule t) {
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
    public RuleView extension() {
        List<RuleView.Option> list = this.list().stream().map(item -> new RuleView.Option(item.getId(), item.getName())).toList();
        return new RuleView(this.field, this.ruleName, BaseEnum.ONE, list);
    }

    @Override
    public RuleView extension(Long id) {
        UserRule entity = this.getById(id);
        return entity == null ? null : new RuleView(this.field, this.ruleName, BaseEnum.ONE, List.of(new RuleView.Option(entity.getId(), entity.getName())));
    }

    @Override
    public String getField() {
        return this.field;
    }
}
