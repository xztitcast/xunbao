package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.Order;
import com.bfox.xunbao.framework.model.OrderModel;
import com.bfox.xunbao.framework.view.OrderInfoView;
import com.bfox.xunbao.framework.view.OrderView;

/**
 * <p>
 * 任务订单表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
public interface OrderService extends FrameworkService<Order, Long> {

    String IDENTIFIER_SERIAL = "XB_ORDER";

    P<OrderView> getDataList(OrderModel model);

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    OrderInfoView getInfo(Long id);
}
