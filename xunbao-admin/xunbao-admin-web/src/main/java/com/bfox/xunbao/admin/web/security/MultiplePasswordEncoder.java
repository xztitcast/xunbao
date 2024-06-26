package com.bfox.xunbao.admin.web.security;

import cn.hutool.core.lang.Validator;
import com.bfox.xunbao.admin.web.support.MetadataEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 多重密码验证(静态代理增强)
 * @author eden
 * @date 2023/6/26 11:57:57
 */
@Component
public class MultiplePasswordEncoder implements PasswordEncoder {

    private PasswordEncoder passwordEncoder;

    private MultiplePasswordEncoder() {
        this(new BCryptPasswordEncoder());
    }

    private MultiplePasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if(Validator.isHex(rawPassword)) {
            rawPassword = MetadataEncoderParser.parse(rawPassword.toString());
        }
        return this.passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String password = MetadataEncoderParser.parse(rawPassword.toString());
        return this.passwordEncoder.matches(password, encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return this.passwordEncoder.upgradeEncoding(encodedPassword);
    }
}
