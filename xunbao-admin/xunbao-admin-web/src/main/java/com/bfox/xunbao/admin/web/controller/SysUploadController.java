package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.admin.web.config.StorageConfig;
import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.storage.core.StorageTemplate;
import com.bfox.xunbao.common.storage.entity.Storage;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 后台管理上传
 * @author eden
 * @date 2024/4/16 22:48:48
 */
@RestController
@RequestMapping("/sys/upload")
public class SysUploadController {

    @Autowired
    private StorageConfig config;

    private StorageTemplate storageTemplate;

    /**
     * 上传图片
     * @param files
     * @return
     */
    @PostMapping("/image")
    public R image(MultipartFile[] files) {
        MinioClient client = MinioClient.builder()
                .endpoint(config.getDomain())
                .credentials(config.getAccessKey(), config.getSecretKey())
                .region(config.getPoint()).build();
        final String prefix = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        List<Map<String, String>> result = Arrays.stream(files).collect(ArrayList::new, (left, right) -> {
            String originalFilename = right.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String imageName = UUID.randomUUID().toString().replace("-", "") + extName;
            try{
                String suffix = prefix.concat("/").concat(imageName);
                PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(config.getBucketName()).object(suffix).stream(right.getInputStream(), right.getSize(), -1).build();
                client.putObject(objectArgs);
                Map<String, String> map = new HashMap<>();
                map.put("name", imageName);
                map.put("url", config.getDomain().concat(config.getBucketName()).concat("/").concat(suffix));
                left.add(map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }, List::addAll);
        List<Storage> storages = storageTemplate.execute(files);
        return R.ok(storages);
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/file")
    public R file(MultipartFile file) {

        return R.ok();
    }
}
