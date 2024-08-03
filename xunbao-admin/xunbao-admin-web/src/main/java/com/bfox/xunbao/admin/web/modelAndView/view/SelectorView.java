package com.bfox.xunbao.admin.web.modelAndView.view;

import lombok.Getter;
import lombok.Setter;

/**
 * 后台管理系统 视图选择器
 * @author eden
 * @date 2024/8/2 21:35:35
 */
@Getter
@Setter
public class SelectorView<T> {

    /**
     * 选择器label值
     */
    protected String label;

    /**
     * 选择器label -> value 值
     */
    protected T value;

    /**
     * 是否禁用
     */
    protected boolean disabled = false;

    public SelectorView() {

    }

    public SelectorView(String label, T value) {
        this(label, value, false);
    }

    public SelectorView(String label, T value, boolean disabled) {
        this.label = label;
        this.value = value;
        this.disabled = disabled;
    }
}
