package com.bfox.xunbao.common.core.injecter;

import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;

/**
 * @author eden
 * @date 2024/8/10 21:44:44
 */
public interface EasyExcelService<R, T extends BaseModel> {

    P<R> getExcelList(T model);

}
