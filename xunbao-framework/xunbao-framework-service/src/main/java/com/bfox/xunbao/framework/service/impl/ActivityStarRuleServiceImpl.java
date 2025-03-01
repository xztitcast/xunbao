package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.ActivityStarRule;
import com.bfox.xunbao.framework.i.service.ActivityStarRuleService;
import com.bfox.xunbao.framework.mapper.ActivityStarRuleMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动星级规则表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
public class ActivityStarRuleServiceImpl extends ServiceImpl<ActivityStarRuleMapper, ActivityStarRule> implements IService<ActivityStarRule>, ActivityStarRuleService {

}
