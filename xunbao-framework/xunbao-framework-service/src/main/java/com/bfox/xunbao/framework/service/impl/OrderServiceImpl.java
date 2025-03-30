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
import com.bfox.xunbao.common.redis.redisson.RedisIdentifierGenerator;
import com.bfox.xunbao.framework.entity.Develop;
import com.bfox.xunbao.framework.entity.Order;
import com.bfox.xunbao.framework.i.service.DevelopService;
import com.bfox.xunbao.framework.i.service.LabelService;
import com.bfox.xunbao.framework.i.service.OrderService;
import com.bfox.xunbao.framework.mapper.OrderMapper;
import com.bfox.xunbao.framework.model.OrderModel;
import com.bfox.xunbao.framework.view.OrderInfoView;
import com.bfox.xunbao.framework.view.OrderView;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 任务订单表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
@DubboService(interfaceClass = OrderService.class)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IService<Order>, OrderService, InitializingBean {

    private RedisIdentifierGenerator redisIdentifierGenerator;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DevelopService developService;

    @Autowired
    private LabelService labelService;

    @Override
    public P<Order> getBaseList(LimitModel m) {
        OrderModel model = (OrderModel) m;
        IPage<Order> page = new Page<>(model.getPageNum(), model.getPageSize());
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator", model.getCreator());
        queryWrapper.eq(StringUtils.isNotBlank(model.getSerialNumber()),"serial_number", model.getSerialNumber());
        this.page(page, queryWrapper);
        return new P<>(page.getTotal(), page.getRecords());
    }

    @Override
    public Order getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(Order t) {
        String value = this.redisIdentifierGenerator.generate(IDENTIFIER_SERIAL);
        t.setSerialNumber(value);
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(Order t) {
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
    public void afterPropertiesSet() throws Exception {
        this.redisIdentifierGenerator = new SerialNumberRedisIdentifierGenerator(this.redisTemplate);
    }

    @Override
    public P<OrderView> getDataList(OrderModel model) {
        IPage<Order> page = new Page<>(model.getPageNum(), model.getPageSize());
        LambdaQueryWrapper<Order> queryWrapper = Wrappers.lambdaQuery(Order.class)
                .eq(Order::getDevelopId, model.getDevelopId())
                .orderByAsc(Order::getCreated);
        this.page(page, queryWrapper);
        List<OrderView> list = page.getRecords().stream().map(item -> {
            OrderView view = new OrderView();
            view.setId(item.getId());
            view.setName(item.getName());
            view.setType(item.getType());
            view.setStatus(item.getStatus());
            view.setSerialNumber(item.getSerialNumber());
            view.setLabel(this.labelService.coverToName(item.getLabel()));
            return view;
        }).toList();
        return new P<>(page.getTotal(), page.getPages(), list);
    }

    @Override
    public OrderInfoView getInfo(Long id) {
        Order entity = this.getById(id);
        if(entity == null) return null;
        Develop develop = this.developService.getEntity(entity.getDevelopId());
        List<String> labels = this.labelService.coverToName(entity.getLabel());
        OrderInfoView view = new OrderInfoView(entity);
        view.setLabel(labels);
        view.setDevelop(develop.getName());
        return view;
    }

    /**
     * 使用redis自增实现编号生成，但是需要配合锁的使用，如果后台用户较多可能编号就重复了
     */
    public static class SerialNumberRedisIdentifierGenerator implements RedisIdentifierGenerator {

        private static final String[] ALPHABET = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        private RedisTemplate<String, String> redisTemplate;

        public SerialNumberRedisIdentifierGenerator(RedisTemplate<String, String> redisTemplate) {
            this.redisTemplate = redisTemplate;
        }

        @Override
        public String generate(String prefix) {
            for(String field : ALPHABET) {
                Long increment = this.redisTemplate.opsForHash().increment(prefix, field, 1);
                if(increment < 9999999) {
                    return field.concat(String.format("%07d", increment));
                }
            }
            return ""; //每个字母可以使用10000000个，26个相当于26亿，如果这都用完，那就要扩容了
        }
    }
}
