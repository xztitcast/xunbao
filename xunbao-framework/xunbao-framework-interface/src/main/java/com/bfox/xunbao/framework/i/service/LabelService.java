package com.bfox.xunbao.framework.i.service;

import com.bfox.xunbao.common.core.injecter.FrameworkService;
import com.bfox.xunbao.framework.entity.Label;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author Eden
 * @since 2025-03-04 01:28:33
 */
public interface LabelService extends FrameworkService<Label, Integer> {

    /**
     * 获取标签选择器数据
     * @return
     */
    List<Label> getSelection();

    /**
     * 转换
     * @param labelIds
     * @return
     */
    List<String> coverToName(String labelIds);
}
