package com.bfox.xunbao.admin.web.support.excel;

import cn.hutool.core.util.ZipUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.bfox.xunbao.admin.web.support.SpringContextManager;
import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.injecter.EasyExcelService;
import com.bfox.xunbao.common.storage.core.StorageTemplate;

import java.io.File;

/**
 * @author eden
 * @date 2024/8/10 21:29:29
 */
public final class SimpleExcelWriter<R, T extends BaseModel> {

    private static final int DEFAULT_PAGE_SIZE = 1000;

    /**
     * 对象
     */
    private EasyExcelService<R, T> easyExcelService;

    /**
     * 结果集
     */
    private Class<R> classR;

    /**
     * 参数
     */
    private T query;


    public SimpleExcelWriter(EasyExcelService<R, T> easyExcelService, T query) {
        this.easyExcelService = easyExcelService;
        this.query = query;
        this.query.setPageSize(DEFAULT_PAGE_SIZE);
    }

    public void export(String name) {
        String tempDir = "D:\\temp\\";
        String tempFileName = tempDir.concat(name).concat(".xlsx");
        try(ExcelWriter excelWriter = EasyExcel.write(tempFileName, this.classR).build()){
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            P<R> first = this.easyExcelService.getExcelList(this.query);
            excelWriter.write(first.getPageList(), writeSheet);
            long total = first.getTotal() / DEFAULT_PAGE_SIZE + (first.getTotal() % DEFAULT_PAGE_SIZE == 0 ? 0 : 1);
            for(int i = 2; i <= total; i++) {
                if((i % 2) == 0) {
                    writeSheet = EasyExcel.writerSheet("模板" + i).build();
                }
                this.query.setPageNum(i);
                P<R> next = this.easyExcelService.getExcelList(this.query);
                excelWriter.write(next.getPageList(), writeSheet);
            }
            excelWriter.finish();
        }
        File zipFile = ZipUtil.zip(tempFileName);

        StorageTemplate storageTemplate = SpringContextManager.getBean(StorageTemplate.class);

        storageTemplate.execute(zipFile);
    }
}
