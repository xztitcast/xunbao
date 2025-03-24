package com.bfox.xunbao.sso.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.sso.entity.User;
import com.bfox.xunbao.sso.i.service.UserService;
import com.bfox.xunbao.sso.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Eden
 * @since 2025-03-24 23:01:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User>, UserService {

    @Override
    public P<User> getBaseList(LimitModel m) {
        return UserService.super.getBaseList(m);
    }

    @Override
    public User getEntity(Long id) {
        return this.getById(id);
    }

    @Override
    @Transactional
    public Long saveEntity(User t) {
        this.save(t);
        return t.getId();
    }

    @Override
    @Transactional
    public boolean updateEntity(User t) {
        return this.updateById(t);
    }

    @Override
    @Transactional
    public boolean delete(Collection<Long> ids) {
        return this.removeByIds(ids);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return this.removeById(id);
    }

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getUsername, username);
        return this.getOne(queryWrapper);
    }

    @Override
    public User getByUnionId(String unionId) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getUnionId, unionId);
        return this.getOne(queryWrapper);
    }

    @Override
    public User getByUuid(String uuid) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getUuid, uuid);
        return this.getOne(queryWrapper);
    }

    @Override
    public User getByOpenId(String openId) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery(User.class).eq(User::getOpenid, openId);
        return this.getOne(queryWrapper);
    }
}
