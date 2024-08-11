package com.bfox.xunbao.common.storage.core;

import com.bfox.xunbao.common.storage.connection.StorageClient;
import com.bfox.xunbao.common.storage.connection.StorageClientFactory;
import com.bfox.xunbao.common.storage.entity.Storage;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 存储操作模版
 * @author eden
 * @date 2024/4/21 19:45:45
 */
public class StorageTemplate implements StorageOperations {

    private static final Logger log = LoggerFactory.getLogger(StorageTemplate.class);

    private StorageClient storageClient;

    public StorageTemplate(StorageClientFactory storageClientFactory) {
        this.storageClient = storageClientFactory.createStorageClient();
    }

    @Override
    public Storage execute(MultipartFile file) {
        try(BufferedInputStream bis = new BufferedInputStream(file.getInputStream())){
            if(FileMagic.UNKNOWN == FileMagic.valueOf(bis)) {
                throw new RuntimeException("文件格式不正确, 请重新上传!");
            }
            String prefix = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String imageName = UUID.randomUUID().toString().replace("-", "") + extName;
            String suffix = prefix.concat("/").concat(imageName);
            return new Storage(imageName, this.storageClient.putObject(bis, file.getSize(), suffix));
        }catch (Exception e) {
            log.error("上传文件失败", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Storage execute(File file) {
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            String prefix = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
            String suffix = prefix.concat("/").concat(file.getName());
            return new Storage(file.getName(), this.storageClient.putObject(bis, file.length(), suffix));
        }catch (Exception e) {
            log.error("上传文件失败", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean execute(Storage entity) {
        try {
            this.storageClient.deleteObject(entity.getUrl());
            return true;
        }catch (Exception e){
            log.error("文件删除失败", e);
            return false;
        }
    }
}
