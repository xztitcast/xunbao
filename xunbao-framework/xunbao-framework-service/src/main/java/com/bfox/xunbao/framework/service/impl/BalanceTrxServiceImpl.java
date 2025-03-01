package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.BalanceTrx;
import com.bfox.xunbao.framework.i.service.BalanceTrxService;
import com.bfox.xunbao.framework.mapper.BalanceTrxMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 保证金划扣表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
public class BalanceTrxServiceImpl extends ServiceImpl<BalanceTrxMapper, BalanceTrx> implements IService<BalanceTrx>, BalanceTrxService {

}
