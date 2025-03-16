package com.bfox.xunbao.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.framework.entity.UserKabaw;
import com.bfox.xunbao.framework.i.service.UserKabawService;
import com.bfox.xunbao.framework.mapper.UserKabawMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户卡包表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-16 14:23:28
 */
@Service
public class UserKabawServiceImpl extends ServiceImpl<UserKabawMapper, UserKabaw> implements IService<UserKabaw>, UserKabawService {

}
