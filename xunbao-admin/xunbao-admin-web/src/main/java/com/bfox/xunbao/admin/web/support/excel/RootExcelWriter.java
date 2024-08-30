package com.bfox.xunbao.admin.web.support.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.bfox.xunbao.admin.web.support.SpringContextManager;
import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.injecter.ExcelWriterService;
import com.bfox.xunbao.common.core.utils.FileUtils;
import com.bfox.xunbao.common.storage.core.StorageTemplate;
import com.bfox.xunbao.common.storage.entity.Storage;

import java.io.File;

/**
 * 自定义alibaba easy excel 写入器
 * @author eden
 * @date 2024/8/22 23:02:02
 */
public final class RootExcelWriter<T extends LimitModel, R> implements IExcelWriter<T, R> {

    private static final int DEFAULT_BATCH_SIZE = Constants.DEFAULT_BATCH_SIZE;

    private static final int DEFAULT_SHEET_SIZE = DEFAULT_BATCH_SIZE * 100;

    private final ExcelWriterService<T, R> excelWriterService;

    /**
     * 结果集
     */
    private Class<R> entityClass;

    /**
     * 参数
     */
    private T model;

    public RootExcelWriter(ExcelWriterService<T, R> excelWriterService, T model, Class<R> entityClass) {
        this.excelWriterService = excelWriterService;
        this.model = model;
        this.entityClass = entityClass;
        this.model.setPageSize(Constants.DEFAULT_BATCH_SIZE);
    }

    @Override
    public void write(ExcelContext context) {
        File tempDir = FileUtils.createTempDirectory(null);
        StringBuilder tempFile = new StringBuilder(tempDir.getAbsolutePath());
        tempFile.append(File.separator).append(context.getHash()).append(context.getFileName());
        log.info("临时文件创建成功:{}", tempFile);
        try(ExcelWriter excelWriter = EasyExcel.write(tempFile.toString(), this.entityClass).build()) {
            P<R> list = this.excelWriterService.getExcelList(this.model);
            WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();
            excelWriter.write(list.getPageList(), writeSheet);
            long total = list.getTotal();
            long page = (total / DEFAULT_BATCH_SIZE) + (total % DEFAULT_BATCH_SIZE == 0 ? 0 : 1);
            for(long i = 2; i <= page; i++) {
                if(i % 100 == 0) {
                    int index = (int)(i * DEFAULT_BATCH_SIZE) / DEFAULT_SHEET_SIZE + 1;
                    writeSheet = EasyExcel.writerSheet(index, "Sheet" + index).build();
                }
                excelWriter.write(list.getPageList(), writeSheet);
            }
            excelWriter.finish();
            File zipFile = ZipUtil.zip(tempFile.toString());
            StorageTemplate storageTemplate = SpringContextManager.getBean(StorageTemplate.class);
            Storage storage = storageTemplate.execute(zipFile);
        } finally {
            FileUtil.del(tempDir);
        }
    }
}
