package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.ActivityOrder;
import com.bfox.xunbao.framework.i.service.ActivityOrderService;
import com.bfox.xunbao.framework.mapper.ActivityOrderMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动订单表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 14:23:28
 */
@Service
public class ActivityOrderServiceImpl extends ServiceImpl<ActivityOrderMapper, ActivityOrder> implements IService<ActivityOrder>, ActivityOrderService {

}
