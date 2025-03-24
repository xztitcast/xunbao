package com.bfox.xunbao.sso.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.sso.entity.UserInfo;
import com.bfox.xunbao.sso.i.service.UserInfoService;
import com.bfox.xunbao.sso.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-24 23:01:11
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IService<UserInfo>, UserInfoService {

}
