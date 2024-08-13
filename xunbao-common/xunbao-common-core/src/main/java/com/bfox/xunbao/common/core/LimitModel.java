package com.bfox.xunbao.common.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础查询条件参数(映射前端form表单提交)
 * 定义分页、排序条件
 * 所有请求分页条件对象都需要集成该类
 * @author eden
 * @date 2022/10/22 22:43:43
 */
@Getter
@Setter
public class LimitModel extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
     * 分页页码
     */
    protected Integer pageNum;

    /**
     * 分页页量
     */
    protected Integer pageSize;

    /**
     * 排序 ASC = true DESC = false
     */
    protected Boolean orderByAsc;

    /**
     * 需要排序字段
     */
    protected String orderField;

    public LimitModel(){
        this(1, 10);
    }

    public LimitModel(Integer pageNum, Integer pageSize){
        this.pageNum=pageNum;
        this.pageSize=pageSize;
        this.orderByAsc = true;
        this.orderField = "created";
    }
}
