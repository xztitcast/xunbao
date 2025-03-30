package com.bfox.xunbao.framework.view;


import com.bfox.xunbao.framework.entity.Develop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 开发语言数据返回
 * @Author Eden
 * @Date 2025/3/30 18:45
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevelopView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    public DevelopView(Develop develop) {
        this.id = develop.getId();
        this.name = develop.getName();
    }
}
