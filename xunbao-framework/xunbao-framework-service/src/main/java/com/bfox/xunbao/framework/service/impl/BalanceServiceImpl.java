package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.Balance;
import com.bfox.xunbao.framework.i.service.BalanceService;
import com.bfox.xunbao.framework.mapper.BalanceMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 保证金表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements IService<Balance>,  BalanceService {

}
