package com.bfox.xunbao.admin.web.controller;

import com.bfox.xunbao.common.core.R;
import com.bfox.xunbao.common.storage.core.StorageTemplate;
import com.bfox.xunbao.common.storage.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 后台管理上传
 * @author eden
 * @date 2024/4/16 22:48:48
 */
@RestController
@RequestMapping("/sys/upload")
public class SysUploadController {

    @Autowired
    private StorageTemplate storageTemplate;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("/save")
    public R save(MultipartFile file) {
        Storage entity = storageTemplate.execute(file);
        return R.ok(entity);
    }

    /**
     * 删除图片
     * @param storage
     * @return
     */
    @DeleteMapping("/delete")
    public R delete(@RequestBody Storage storage) {
        boolean execute = this.storageTemplate.execute(storage);
        if(execute) {
            return R.ok();
        }else {
            return R.error();
        }
    }

}
