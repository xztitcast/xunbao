package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.framework.entity.BalanceTrx;
import com.bfox.xunbao.framework.i.service.BalanceTrxService;
import com.bfox.xunbao.framework.mapper.BalanceTrxMapper;
import com.bfox.xunbao.framework.model.BalanceTrxModel;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 保证金划扣表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
@DubboService(interfaceClass = BalanceTrxService.class)
public class BalanceTrxServiceImpl extends ServiceImpl<BalanceTrxMapper, BalanceTrx> implements IService<BalanceTrx>, BalanceTrxService {

    @Override
    public P<BalanceTrx> getBaseList(LimitModel m) {
        BalanceTrxModel model = (BalanceTrxModel) m;
        IPage<BalanceTrx> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<BalanceTrx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(model.getOrderWorkId() != null, "order_work_id", model.getOrderWorkId());
        queryWrapper.eq(StringUtils.isNotBlank(model.getSerialNumber()), "serial_number", model.getSerialNumber());
        queryWrapper.orderBy(true, model.getOrderByAsc(), model.getOrderField());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public BalanceTrx getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(BalanceTrx t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(BalanceTrx t) {
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
