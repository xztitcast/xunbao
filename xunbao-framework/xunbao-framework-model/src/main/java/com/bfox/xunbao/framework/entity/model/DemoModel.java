package com.bfox.xunbao.framework.entity.model;

import com.bfox.xunbao.common.core.ModelAndView;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author eden
 * @date 2024/8/23 16:01:01
 */
@Getter
@Setter
public class DemoModel extends ModelAndView<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String value;

    private String message;
}
