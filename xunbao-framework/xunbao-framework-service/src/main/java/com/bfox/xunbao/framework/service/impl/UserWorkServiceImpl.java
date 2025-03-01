package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.UserWork;
import com.bfox.xunbao.framework.i.service.UserWorkService;
import com.bfox.xunbao.framework.mapper.UserWorkMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户工作台 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
@Service
public class UserWorkServiceImpl extends ServiceImpl<UserWorkMapper, UserWork> implements IService<UserWork>, UserWorkService {

}
