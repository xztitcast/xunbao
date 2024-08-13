package com.bfox.xunbao.admin.web.support.handlers;

/**
 * 后台管理系统对象填充器
 * @author eden
 * @date 2024/8/13 21:43:43
 */
public interface ObjectFillHandler<T> {

    /**
     * 填充
     * @param object
     */
    void doFill(T object);
}
