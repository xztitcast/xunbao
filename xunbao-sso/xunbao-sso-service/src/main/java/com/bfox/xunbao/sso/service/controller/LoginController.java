package com.bfox.xunbao.sso.service.controller;

import com.bfox.xunbao.common.core.injecter.Principal;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.security.session.AuthenticationTokenWebManager;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    /**
     * 账号登录
     * @param username
     * @param code
     * @return
     */
    @PostMapping("/account")
    public R account(@NotBlank(message = "手机号码不能为空!") @RequestParam String username,
                     @NotBlank(message = "验证码不能为空!") @RequestParam String code) {
        Principal p = new Principal(1L, "测试登录", "账号登录");
        String token = this.authenticationTokenWebManager.createToken(p);
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
