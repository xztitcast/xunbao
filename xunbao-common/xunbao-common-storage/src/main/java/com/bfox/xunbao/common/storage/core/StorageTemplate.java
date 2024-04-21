package com.bfox.xunbao.common.storage.core;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import com.bfox.xunbao.common.storage.connection.StorageClientFactory;
import com.bfox.xunbao.common.storage.entity.Storage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 存储操作模版
 * @author eden
 * @date 2024/4/21 19:45:45
 */
public class StorageTemplate implements StorageOperations {

    private StorageClient storageClient;

    public StorageTemplate(StorageClientFactory storageClientFactory) {
        this.storageClient = storageClientFactory.createStorageClient();
    }

    @Override
    public Storage execute(MultipartFile file) {
        List<Storage> list = execute(new MultipartFile[]{file});
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Storage> execute(MultipartFile[] files) {
        return null;
    }
}
