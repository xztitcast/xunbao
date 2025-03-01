package com.bfox.xunbao.common.redis.redisson;


/**
 * redis订单编号生成器
 * @Author Eden
 * @Date 2025/3/1 14:44
 */
public interface RedisIdentifierGenerator {

    String generate(String prefix);
}
