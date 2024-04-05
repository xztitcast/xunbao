package com.bfox.xunbao.setup.i.service;

import com.bfox.xunbao.setup.entity.ContentCat;
import com.bfox.xunbao.common.core.injecter.FrameworkService;

import java.util.List;

/**
 * <p>
 * 内容分类表 服务类
 * </p>
 *
 * @author Eden
 * @since 2024-04-02 23:41:38
 */
public interface ContentCatService extends FrameworkService<ContentCat, Long> {

    List<ContentCat> getContentCatList(Long parentId, Long tenantId);
}
