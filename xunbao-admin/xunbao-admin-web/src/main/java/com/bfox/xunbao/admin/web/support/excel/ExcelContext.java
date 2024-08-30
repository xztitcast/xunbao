package com.bfox.xunbao.admin.web.support.excel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Excel上下文
 * @author eden
 * @date 2024/8/22 22:42:42
 */
@Getter
@Setter
public class ExcelContext implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * hash值(防止覆盖)
     */
    private String hash;

    /**
     * 文件上传路径
     */
    private String path;

    public ExcelContext() {

    }

    public ExcelContext(String fileName, String hash) {
        this.fileName = fileName;
        this.hash = hash;
    }
}
