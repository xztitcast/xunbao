package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.ActivityUserRule;
import com.bfox.xunbao.framework.i.service.ActivityUserRuleService;
import com.bfox.xunbao.framework.mapper.ActivityUserRuleMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动用户规则表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
public class ActivityUserRuleServiceImpl extends ServiceImpl<ActivityUserRuleMapper, ActivityUserRule> implements IService<ActivityUserRule>, ActivityUserRuleService {

}
