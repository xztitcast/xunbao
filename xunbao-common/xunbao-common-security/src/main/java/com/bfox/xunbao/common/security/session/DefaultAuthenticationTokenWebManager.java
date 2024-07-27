package com.bfox.xunbao.common.security.session;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSON;
import com.bfox.xunbao.common.core.injecter.Principal;
import com.bfox.xunbao.common.core.enums.BaseEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 默认Token认证管理器
 * @author eden
 * @date 2023/9/23 22:29:29
 */
public class DefaultAuthenticationTokenWebManager implements AuthenticationTokenWebManager {

    private Pattern pattern = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-:._~+/]+=*)$", Pattern.CASE_INSENSITIVE);

    private RedisTemplate<String, String> redisTemplate;

    public DefaultAuthenticationTokenWebManager(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Override
    public String createToken(Principal principal) {
        String token = DigestUtil.md5Hex(UUID.randomUUID().toString() + principal.getId()).toUpperCase();
        this.redisTemplate.opsForValue().set(TOKEN_KEY.concat(token), JSON.toJSONString(principal), BaseEnum.SEVEN, TimeUnit.DAYS);
        this.redisTemplate.opsForValue().set(TOKEN_KEY + principal.getId(), token, BaseEnum.SEVEN, TimeUnit.DAYS);
        return null;
    }

    @Override
    public Principal getByToken(String token) {
        if(StringUtils.isBlank(token)) {
            return null;
        }
        Matcher matcher = pattern.matcher(token);
        if(!matcher.matches()) {
            return null;
        }
        String match = matcher.group("token");
        if(StringUtils.isBlank(match)) {
            return null;
        }
        String value = this.redisTemplate.opsForValue().get(TOKEN_KEY.concat(match));
        Principal principal = JSON.parseObject(value, Principal.class);
        if(principal != null) {
            this.redisTemplate.expire(TOKEN_KEY.concat(match), BaseEnum.SEVEN, TimeUnit.DAYS);
            this.redisTemplate.expire(TOKEN_KEY + principal.getId(), BaseEnum.SEVEN, TimeUnit.DAYS);
        }
        return principal;
    }

    @Override
    public Principal getByUerId(Long userId) {
        String token = this.redisTemplate.opsForValue().get(TOKEN_KEY + userId);
        String value = this.redisTemplate.opsForValue().get(TOKEN_KEY.concat(token));
        return JSON.parseObject(value, Principal.class);
    }

    @Override
    public boolean remove(String token) {
        if(StringUtils.isBlank(token)) {
            return false;
        }
        Matcher matcher = pattern.matcher(token);
        if(!matcher.matches()) {
            return false;
        }
        String match = matcher.group("token");
        if(StringUtils.isBlank(match)) {
            return false;
        }
        return this.redisTemplate.delete(TOKEN_KEY.concat(token));
    }

    @Override
    public void initLocalCache() {
        LocalCacheManager.setTokenSessionManager(this);
    }
}
