package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.framework.entity.Balance;
import com.bfox.xunbao.framework.i.service.BalanceService;
import com.bfox.xunbao.framework.mapper.BalanceMapper;
import com.bfox.xunbao.framework.model.BalanceModel;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 保证金表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
@DubboService(interfaceClass = BalanceService.class)
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements IService<Balance>, BalanceService {

    @Override
    public P<Balance> getBaseList(LimitModel m) {
        BalanceModel model = (BalanceModel) m;
        IPage<Balance> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<Balance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(model.getUserId() != null, "user_id", model.getUserId());
        queryWrapper.eq(StringUtils.isNotBlank(model.getUsername()), "username", model.getUsername());
        queryWrapper.orderBy(true, model.getOrderByAsc(), model.getOrderField());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public Balance getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(Balance t) {
        this.save(t);
        return t.getId();
    }


    @Override
    @Transactional
    public boolean updateEntity(Balance t) {
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
    public Balance getBalance(String userId) {
        LambdaQueryWrapper<Balance> wrapper = Wrappers.lambdaQuery(Balance.class).eq(Balance::getUserId, userId);
        return this.getOne(wrapper);
    }
}
