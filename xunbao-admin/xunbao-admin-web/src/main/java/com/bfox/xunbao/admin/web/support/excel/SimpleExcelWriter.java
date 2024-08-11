package com.bfox.xunbao.admin.web.support.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.bfox.xunbao.admin.web.support.SpringContextManager;
import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.common.core.injecter.EasyExcelService;
import com.bfox.xunbao.common.storage.core.StorageTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author eden
 * @date 2024/8/10 21:29:29
 */
public final class SimpleExcelWriter<R, T extends BaseModel> {

    private static final int DEFAULT_SHEET_SIZE = 100000;

    private static final int DEFAULT_PAGE_SIZE = 50;

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


    public SimpleExcelWriter(EasyExcelService<R, T> easyExcelService, T query, Class<R> classR) {
        this.easyExcelService = easyExcelService;
        this.query = query;
        this.classR = classR;
        this.query.setPageSize(DEFAULT_PAGE_SIZE);
    }

    public void export(String name) {
        String tempDir = "D:\\temp\\";
        File zipFile = new File(tempDir.concat(name) + ".zip");
        try(ExcelWriter excelWriter = EasyExcel.write(tempDir.concat(name).concat(".xlsx"), this.classR).build();
            ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))){
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
            ZipEntry zipEntry = new ZipEntry(zipFile.getName());
            zos.putNextEntry(zipEntry);
            Files.copy(zipFile.toPath(), zos);
            zos.closeEntry();
        }catch (IOException e) {

        }

        StorageTemplate storageTemplate = SpringContextManager.getBean(StorageTemplate.class);
    }
}
