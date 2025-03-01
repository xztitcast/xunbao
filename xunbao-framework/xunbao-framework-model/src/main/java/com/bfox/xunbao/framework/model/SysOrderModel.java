package com.bfox.xunbao.framework.model;


import com.bfox.xunbao.common.core.LimitModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author Eden
 * @Date 2025/3/1 15:28
 */
@Getter
@Setter
public class SysOrderModel extends LimitModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serialNumber;

}
