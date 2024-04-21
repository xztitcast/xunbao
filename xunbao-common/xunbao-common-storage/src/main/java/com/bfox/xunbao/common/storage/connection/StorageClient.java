package com.bfox.xunbao.common.storage.connection;

import java.io.InputStream;

/**
 * @author eden
 * @date 2024/4/21 19:38:38
 */
public interface StorageClient {

    /**
     * 上传
     * @param inputStream 流
     * @param size 大小
     * @param extName 文件名
     */
    void putObject(InputStream inputStream, long size, String extName);

    /**
     * 上传
     * @param content 字节
     * @param extName 文件名
     */
    void putObject(byte[] content, String extName);

    void deleteObject();

}
