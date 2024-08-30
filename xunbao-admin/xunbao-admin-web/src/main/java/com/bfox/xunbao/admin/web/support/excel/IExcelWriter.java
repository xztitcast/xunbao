package com.bfox.xunbao.admin.web.support.excel;

import com.bfox.xunbao.common.core.LimitModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel数据写入器
 * @author eden
 * @date 2024/8/22 22:49:49
 */

public interface IExcelWriter<T extends LimitModel, R> {

    Logger log = LoggerFactory.getLogger(IExcelWriter.class);

    /**
     * 写入数据
     * @param context
     */
    void write(ExcelContext context);
}
