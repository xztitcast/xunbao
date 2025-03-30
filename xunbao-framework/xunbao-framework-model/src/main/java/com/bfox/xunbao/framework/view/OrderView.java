package com.bfox.xunbao.framework.view;


import com.bfox.xunbao.common.core.ModelAndView;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单视图(援军令)
 * @Author Eden
 * @Date 2025/3/26 22:16
 */
@Getter
@Setter
public class OrderView extends ModelAndView<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    protected String serialNumber;

    /**
     * 任务名称
     */
    protected String name;

    /**
     * 后台状态3:已发布 4:进行中 5:已结束
     */
    protected Integer status;

    /**
     * 类型(1:需求 2:BUG)
     */
    protected Integer type;

    /**
     * 周期
     */
    protected Integer cycle;

    /**
     * 标签
     */
    protected List<String> label;

    /**
     * 发布时间
     */
    protected Date publishTime;

}
