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
import com.bfox.xunbao.framework.entity.CycleRule;
import com.bfox.xunbao.framework.i.service.CycleRuleService;
import com.bfox.xunbao.framework.mapper.CycleRuleMapper;
import com.bfox.xunbao.framework.model.SysCommonModel;
import com.bfox.xunbao.framework.view.RuleView;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 活动周期规则表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
@RuleType(BaseEnum.TWO)

@DubboService(interfaceClass = CycleRuleService.class)
public class CycleRuleServiceImpl extends ServiceImpl<CycleRuleMapper, CycleRule> implements IService<CycleRule>, CycleRuleService {

    private final String field = "cycleRule";

    private final String ruleName = "周期规则";

    @Override
    public P<CycleRule> getBaseList(LimitModel m) {
        SysCommonModel model = (SysCommonModel) m;
        IPage<CycleRule> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<CycleRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(model.getName()), "name", model.getName());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public CycleRule getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    public Long saveEntity(CycleRule t) {
        this.save(t);
        return t.getId();
    }

    @Override
    public boolean updateEntity(CycleRule t) {
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

    @Override
    public RuleView extension() {
        List<RuleView.Option> list = this.list().stream().map(item -> new RuleView.Option(item.getId(), item.getName())).toList();
        return new RuleView(this.field, this.ruleName, BaseEnum.TWO, list);
    }

    @Override
    public RuleView extension(Long id) {
        CycleRule entity = this.getById(id);
        return entity == null ? null : new RuleView(this.field, this.ruleName, BaseEnum.TWO, List.of(new RuleView.Option(entity.getId(), entity.getName())));
    }

    @Override
    public String getField() {
        return this.field;
    }
}
