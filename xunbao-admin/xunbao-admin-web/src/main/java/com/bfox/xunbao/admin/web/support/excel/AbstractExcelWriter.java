package com.bfox.xunbao.admin.web.support.excel;

import com.bfox.xunbao.common.core.LimitModel;

/**
 * Excel数据写入装饰器
 * @author eden
 * @date 2024/9/8 18:22:22
 */
public abstract class AbstractExcelWriter<T extends LimitModel, R> implements IExcelWriter<T, R> {

    /**
     * 对RootExcelWriter进行装饰
     */
    private IExcelWriter<T, R> excelWriter;

    public AbstractExcelWriter(IExcelWriter<T, R> excelWriter) {
        this.excelWriter = excelWriter;
    }

    @Override
    public void write(ExcelContext context) {
        this.excelWriter.write(context);
    }

    protected abstract void save(ExcelContext context);
}
