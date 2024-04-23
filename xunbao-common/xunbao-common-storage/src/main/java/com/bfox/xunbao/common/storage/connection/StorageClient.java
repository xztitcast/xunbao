package com.bfox.xunbao.common.storage.connection;

import java.io.InputStream;

/**
 * @author eden
 * @date 2024/4/21 19:38:38
 */
public interface StorageClient {

    /**
     * 上传
     * @param is 流
     * @param size 大小
     * @param extName 文件名
     */
    String putObject(InputStream is, long size, String extName) throws Exception;

    /**
     * 上传
     * @param content 字节
     * @param extName 文件名
     */
    String putObject(byte[] content, String extName) throws Exception;

    /**
     * 删除图片
     * @param url 图片地址
     */
    void deleteObject(String url) throws Exception;

}
