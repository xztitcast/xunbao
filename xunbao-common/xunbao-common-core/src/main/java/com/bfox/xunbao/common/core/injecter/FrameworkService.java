package com.bfox.xunbao.common.core.injecter;

import java.io.Serializable;
import java.util.Collection;

import com.bfox.xunbao.common.core.LimitModel;
import com.bfox.xunbao.common.core.P;


/**
 * 基本Service接口、封装比较常用的方法提高复用率、经常会在后台管理相关接口用到
 * @author eden
 * @date 2023年2月10日 下午2:52:31
 * @param <T>
 * @param <ID>
 */
public interface FrameworkService<T, ID extends Serializable> {

	/**
	 * 分页获取基准数据列表
	 * @param m
	 * @return
	 */
	default P<T> getBaseList(LimitModel m){return null;};
	
	/**
	 * 获取单个对象
	 * @param id
	 * @return
	 */
	default T getEntity(ID id) {return null;};
	
	/**
	 * 保存单个对象
	 * @param t
	 * @return
	 */
	default ID saveEntity(T t) {return null;};
	
	/**
	 * 修改单个对象
	 * @param t
	 * @return
	 */
	default boolean updateEntity(T t) {return false;};
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	default boolean delete(Collection<ID> ids) {return false;};

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	default boolean delete(ID id){return false;};
}
