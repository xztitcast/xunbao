package com.bfox.xunbao.framework.view;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 后台管理页面动态规则参数
 * @Author Eden
 * @Date 2025/3/19 22:45
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性名
     */
    private String field;

    /**
     * 规则名
     */
    private String ruleName;

    /**
     * 规则类型
     */
    private Integer ruleType;

    /**
     * 规则选项
     */
    private List<Option> options;

    /**
     * 选择器option对象
     * 与管理后台页面的数据结构保持一致
     * 详情见activity-add-or-update.vue 页面的ruleList规则数据结构
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Option implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 主键ID
         */
        private Long id;

        /**
         * 名称
         */
        private String name;
    }
}
