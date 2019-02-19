package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbItemCat;

/**
 * @Description:
 */
public interface ItemCatService {
	public PageResult searchByParentId(int pageNum,int pageSize,Long parentId);
	public void delete(Long[] ids) throws NullPointerException;
	public void add(TbItemCat itemCat) throws NullPointerException;
	public TbItemCat findOne(Long id);
	public void update(TbItemCat itemCat) throws NullPointerException;
}
