package com.bfox.xunbao.setup.model;

import java.io.Serial;

import com.bfox.xunbao.common.core.LimitModel;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 查询列表数据 的查询条件
 * @author: lujianxiong
 * @date: 2023/3/9 18:32
 */
@Setter
@Getter
public class SysAdContentModel extends LimitModel {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 广告名称
     */
    private String name;

}
