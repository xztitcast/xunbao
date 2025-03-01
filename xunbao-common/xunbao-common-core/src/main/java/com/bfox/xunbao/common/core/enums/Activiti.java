package com.bfox.xunbao.common.core.enums;

import lombok.Getter;

/**
 * 审批流枚举
 * @author eden
 * @date 2024/9/8 22:13:13
 */
@Getter
public enum Activiti {

    APPROVAL_FLOW_TYPE_FILE(1, "数据导出审核", "dataCenterServiceImpl"),

    APPROVAL_ORDER_TYPE_FILE(2, "数据导出审核", "dataCenterServiceImpl");

    /**
     * 类型(自定义)
     */
    private int type;

    /**
     * 描述(冗余字段)
     */
    private String name;

    /**
     * Spring bean 名称
     */
    private String beanName;

    private Activiti(int type, String name, String beanName) {
        this.type = type;
        this.name = name;
        this.beanName = beanName;
    }

    public static Activiti get(int type) {
        for (Activiti activiti : Activiti.values()) {
            if (activiti.getType() == type) {
                return activiti;
            }
        }
        return null;
    }
}
