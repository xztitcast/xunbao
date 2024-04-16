package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.common.core.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * 后台管理上传
 * @author eden
 * @date 2024/4/16 22:48:48
 */
@RestController
@RequestMapping("/sys/upload")
public class SysUploadController {

    /**
     * 上传图片
     * @param files
     * @return
     */
    @PostMapping("/image")
    public R image(MultipartFile[] files) {
        Arrays.stream(files).forEach(e -> System.out.println(e.getOriginalFilename()));
        return R.ok();
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
