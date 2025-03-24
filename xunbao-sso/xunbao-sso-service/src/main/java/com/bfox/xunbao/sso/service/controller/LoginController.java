package com.bfox.xunbao.sso.service.controller;

import com.alibaba.fastjson2.JSON;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.core.injecter.Principal;
import com.bfox.xunbao.common.security.session.AuthenticationTokenWebManager;
import com.bfox.xunbao.sso.entity.User;
import com.bfox.xunbao.sso.i.service.UserService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author eden
 * @date 2023/9/24 11:02:02
 */
@Validated
@RestController
@RequestMapping("/sso/login")
public class LoginController {

    @Autowired
    private AuthenticationTokenWebManager authenticationTokenWebManager;

    @Autowired
    private UserService userService;

    /**
     * 账号登录
     * @param username
     * @param code
     * @return
     */
    @PostMapping("/account")
    public R account(@NotBlank(message = "手机号码不能为空!") @RequestParam String username,
                     @NotBlank(message = "验证码不能为空!") @RequestParam String code) {
        User entity = this.userService.getByUsername(username);
        if(entity == null) {
            entity = new User();
            entity.setUsername(username);
            entity.setAvatar("http://127.0.0:8080/pic/avatar.png");
            entity.setNickname("测试用户001");
            entity.setPassword("123456");
            entity.setIp("127.0.0.1");
            entity.setType(2);
            entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
            this.userService.saveEntity(entity);
        }
        Principal principal = JSON.parseObject(entity.toString(), Principal.class);
        String token = this.authenticationTokenWebManager.create(principal);
        return R.ok(Map.of("token", token));
    }

    /**
     * 小程序登录
     * @return
     */
    @PostMapping("/mini")
    public R mini() {

        return R.ok();
    }
}
