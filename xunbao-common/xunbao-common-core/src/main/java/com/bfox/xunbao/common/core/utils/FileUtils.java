package com.bfox.xunbao.common.core.utils;

import com.bfox.xunbao.common.core.exception.custom.XunbaoServiceException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * 文件工具类
 * @author eden
 * @date 2024/8/30 22:06:06
 */
@Slf4j
public abstract class FileUtils {

    /**
     * 创建临时目录
     * @param prefix
     * @return
     */
    public static File createTempDirectory(String prefix) {
        try{
            return Files.createTempDirectory(prefix).toFile();
        }catch (IOException e) {
            log.error("临时目录创建失败", e);
            throw new XunbaoServiceException(500, "创建临时目录失败");
        }
    }
}
