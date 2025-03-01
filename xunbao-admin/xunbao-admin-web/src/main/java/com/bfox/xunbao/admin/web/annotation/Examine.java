package com.bfox.xunbao.admin.web.annotation;


import com.bfox.xunbao.common.core.enums.Activiti;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 审查注解
 * @Author Eden
 * @Date 2025/3/1 16:42
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Examine {

    Activiti value();
}
