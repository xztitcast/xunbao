package com.bfox.xunbao.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bfox.xunbao.framework.entity.OrderWork;
import com.bfox.xunbao.framework.model.SysOrderWorkModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单任务表 Mapper 接口
 * </p>
 *
 * @author Eden
 * @since 2025-03-01 13:38:50
 */
public interface OrderWorkMapper extends BaseMapper<OrderWork> {

    List<OrderWork> selectOrderWorkList(IPage<OrderWork> page, @Param("model") SysOrderWorkModel model);
}
