package com.bfox.xunbao.setup.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.setup.entity.AdContent;
import com.bfox.xunbao.setup.i.service.AdContentService;
import com.bfox.xunbao.setup.mapper.AdContentMapper;
import com.bfox.xunbao.setup.model.AdContentModel;
import com.bfox.xunbao.setup.model.SysAdContentModel;

/**
 * 广告内容Service业务实现类
 * @author eden
 * @date 2023年2月11日 下午4:33:11
 */
@Service("appAdContentService")
@DubboService(interfaceClass = AdContentService.class)
public class AdContentServiceImpl extends ServiceImpl<AdContentMapper, AdContent> implements IService<AdContent>, AdContentService {

	@Override
	public P<AdContent> getBaseList(BaseModel m) {
		SysAdContentModel model = (SysAdContentModel) m;
		IPage<AdContent> page = new Page<>(m.getPageNum(), m.getPageSize());
		QueryWrapper<AdContent> wrapper = new QueryWrapper<>();
		wrapper.lambda().like(StringUtils.isNotBlank(model.getName()),AdContent::getName,model.getName());
		wrapper.orderBy(true, m.getOrder(), m.getOrderField());
		page(page,wrapper);
		return new P<>(page.getTotal(), page.getRecords());
	}

	@Override
	public AdContent getEntity(Long id) {
		return this.getById(id);
	}

	@Override
	@Transactional
	public AdContent saveEntity(AdContent t) {
		this.save(t);
		return t;
	}

	@Override
	@Transactional
	public boolean updateEntity(AdContent t) {
		return this.updateById(t);
	}

	@Override
	@Transactional
	public boolean delete(Collection<Long> ids) {
		return this.removeBatchByIds(ids);
	}

	@Override
	public List<AdContent> getAdContentList() {
		Date date = new Date();
		LambdaQueryWrapper<AdContent> query = Wrappers.lambdaQuery(AdContent.class).le(AdContent::getStartTime, date).ge(AdContent::getEndTime, date);
		return this.list(query);
	}

	@Override
	public List<AdContent> getAdContentList(AdContentModel model) {
		return this.baseMapper.selectAdContentList(model);
	}

}
