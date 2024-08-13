package com.bfox.xunbao.common.core.injecter;

import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;

/**
 * @author eden
 * @date 2024/8/10 21:44:44
 */
public interface EasyExcelService<R, T extends LimitModel> {

    P<R> getExcelList(T model);

}
