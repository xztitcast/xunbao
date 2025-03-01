package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.Reward;
import com.bfox.xunbao.framework.i.service.RewardService;
import com.bfox.xunbao.framework.mapper.RewardMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 奖励表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward> implements IService<Reward>,  RewardService {

}
