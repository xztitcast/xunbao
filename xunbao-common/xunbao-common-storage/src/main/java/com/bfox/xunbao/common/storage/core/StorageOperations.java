package com.bfox.xunbao.common.storage.core;

import com.bfox.xunbao.common.storage.entity.Storage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author eden
 * @date 2024/4/21 20:13:13
 */
public interface StorageOperations {

    /**
     * 执行上传
     * @param file
     * @return
     */
    Storage execute(MultipartFile file);

    /**
     * 执行上传
     * @param files
     * @return
     */
    List<Storage> execute(MultipartFile[] files);

    /**
     * 执行删除
     * @param entity
     * @return
     */
    boolean execute(Storage entity);
}
