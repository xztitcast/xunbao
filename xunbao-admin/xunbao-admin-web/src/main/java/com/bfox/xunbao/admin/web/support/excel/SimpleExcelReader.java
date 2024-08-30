package com.bfox.xunbao.admin.web.support.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.bfox.xunbao.common.core.injecter.ExcelReaderService;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel数据读取器
 * @author eden
 * @date 2024/8/15 22:43:43
 */
@Slf4j
public class SimpleExcelReader<T> {

    private ExcelReaderService<T> excelReaderService;

    private Class<T> entityClass;

    public SimpleExcelReader(ExcelReaderService<T> excelReaderService, Class<T> entityClass) {
        this.excelReaderService = excelReaderService;
        this.entityClass = entityClass;
    }

    public void read(InputStream is) {
        EasyExcel.read(is, this.entityClass, new SimpleExcelReaderListener<T>(this.excelReaderService)).sheet().doRead();
    }

    /**
     * 数据读取监听器
     * @param <T>
     */
    private static class SimpleExcelReaderListener<T> implements ReadListener<T> {

        private static final int BATCH_COUNT = 100;

        private List<T> dataList = new ArrayList<>(BATCH_COUNT);

        private ExcelReaderService<T> excelReaderService;

        public SimpleExcelReaderListener(ExcelReaderService<T> excelReaderService) {
            this.excelReaderService = excelReaderService;
        }

        @Override
        public void invoke(T t, AnalysisContext analysisContext) {
            if(this.dataList.size() >= BATCH_COUNT) {
                int size = this.dataList.size();
                log.info("{}条数据，开始存储数据库！", size);
                this.excelReaderService.read(this.dataList);
                this.dataList.clear();
                log.info("{}条数据，存储数据库成功", size);
            }else {
                this.dataList.add(t);
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            this.excelReaderService.read(this.dataList);
            log.info("最后{}条数据存储数据库完成,所有数据解析完毕！", this.dataList.size());
        }
    }
}
