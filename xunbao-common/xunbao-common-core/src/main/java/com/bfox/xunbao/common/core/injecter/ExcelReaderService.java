package com.bfox.xunbao.common.core.injecter;

import java.util.List;

/**
 * Excel读取数据接口
 * @author eden
 * @date 2024/8/15 22:44:44
 */
public interface ExcelReaderService<T> {

    /**
     * 读取数据入库
     * @param dataList
     */
    void read(List<T> dataList);
}
