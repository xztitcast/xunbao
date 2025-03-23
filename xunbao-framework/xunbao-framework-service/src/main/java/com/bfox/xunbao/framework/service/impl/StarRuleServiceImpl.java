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
import com.bfox.xunbao.framework.entity.StarRule;
import com.bfox.xunbao.framework.i.service.StarRuleService;
import com.bfox.xunbao.framework.mapper.StarRuleMapper;
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
 * 活动星级规则表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
@RuleType(BaseEnum.THREE)
@DubboService(interfaceClass = StarRuleService.class)
public class StarRuleServiceImpl extends ServiceImpl<StarRuleMapper, StarRule> implements IService<StarRule>, StarRuleService {

    private final String field = "starRule";

    private final String ruleName = "星级规则";

    @Override
    public P<StarRule> getBaseList(LimitModel m) {
        SysCommonModel model = (SysCommonModel)m;
        IPage<StarRule> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<StarRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(model.getName()), "name", model.getName());
        queryWrapper.orderBy(true, model.getOrderByAsc(), model.getOrderField());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public StarRule getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(StarRule t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(StarRule t) {
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
        return new RuleView(this.field, this.ruleName, BaseEnum.THREE, list);
    }

    @Override
    public RuleView extension(Long id) {
        StarRule entity = this.getById(id);
        return entity == null ? null : new RuleView(this.field, this.ruleName, BaseEnum.THREE, List.of(new RuleView.Option(entity.getId(), entity.getName())));
    }

    @Override
    public String getField() {
        return this.field;
    }
}
