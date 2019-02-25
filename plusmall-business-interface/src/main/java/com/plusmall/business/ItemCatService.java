package com.plusmall.business;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbItemCat;

import java.util.List;

/**
 * @Description:
 */
public interface ItemCatService {
	public PageResult searchByParentId(Long parentId);
	public TbItemCat findOne(Long id);
	public List<TbItemCat> findALl();
}
