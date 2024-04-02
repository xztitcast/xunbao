package com.bfox.xunbao.setup.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bfox.xunbao.common.core.BaseModel;
import com.bfox.xunbao.common.core.P;
import com.bfox.xunbao.setup.entity.AdCategory;
import com.bfox.xunbao.setup.i.service.AdCategoryService;
import com.bfox.xunbao.setup.mapper.AdCategoryMapper;
import com.bfox.xunbao.setup.model.SysAdCategoryModel;

/**
 * 广告类目Service业务接口实现
 * @author eden
 * @date 2023年2月11日 下午4:36:36
 */
@Service("appAdCategoryService")
@DubboService(interfaceClass = AdCategoryService.class)
public class AdCategoryServiceImpl extends ServiceImpl<AdCategoryMapper, AdCategory> implements IService<AdCategory>, AdCategoryService {

	@Override
	public P<AdCategory> getBaseList(BaseModel m) {
		SysAdCategoryModel model = (SysAdCategoryModel)m;
		IPage<AdCategory> page = new Page<>(m.getPageNum(), m.getPageSize());
		QueryWrapper<AdCategory> wrapper = new QueryWrapper<>();
		wrapper.lambda().like(StringUtils.isNotBlank(model.getName()),AdCategory::getName,model.getName());
		wrapper.orderBy(true, m.getOrder(), m.getOrderField());
		page(page,wrapper);
		return new P<>(page.getTotal(), page.getRecords());
	}

	@Override
	public AdCategory getEntity(Integer id) {
		return this.getById(id);
	}

	@Override
	@Transactional
	public AdCategory saveEntity(AdCategory t) {
		this.save(t);
		return t;
	}

	@Override
	@Transactional
	public boolean updateEntity(AdCategory t) {
		return this.updateById(t);
	}

	@Override
	@Transactional
	public boolean delete(Collection<Integer> ids) {
		return this.removeBatchByIds(ids);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) {
		return this.removeById(id);
	}

	@Override
	public List<AdCategory> getAdCategoryList() {
		return this.list();
	}

}
