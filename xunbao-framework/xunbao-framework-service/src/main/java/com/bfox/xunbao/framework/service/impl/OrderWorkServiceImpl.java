package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.framework.entity.OrderWork;
import com.bfox.xunbao.framework.i.service.OrderWorkService;
import com.bfox.xunbao.framework.mapper.OrderWorkMapper;
import com.bfox.xunbao.framework.model.OrderWorkModel;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 订单任务表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
@DubboService(interfaceClass = OrderWorkService.class)
public class OrderWorkServiceImpl extends ServiceImpl<OrderWorkMapper, OrderWork> implements IService<OrderWork>, OrderWorkService {

    @Override
    public P<OrderWork> getBaseList(LimitModel m) {
        OrderWorkModel model = (OrderWorkModel) m;
        Page<OrderWork> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<OrderWork> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", model.getSysUserId());
        queryWrapper.eq(StringUtils.isNotBlank(model.getSerialNumber()), "serial_number", model.getSerialNumber());
        queryWrapper.eq(model.getStatus() != null, "status", model.getStatus());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public OrderWork getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public boolean updateEntity(OrderWork t) {
        return this.updateById(t);
    }

    @Override
    @Transactional
    public Long saveEntity(OrderWork t) {
        this.save(t);
        return t.getId();
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
