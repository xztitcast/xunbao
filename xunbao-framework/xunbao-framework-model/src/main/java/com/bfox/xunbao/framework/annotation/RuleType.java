package com.bfox.xunbao.framework.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 规则类型
 * @Author Eden
 * @Date 2025/3/19 22:37
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Inherited
public @interface RuleType {

    int value();
}
