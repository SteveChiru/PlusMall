package com.plusmall.operator;

import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbTypeTemplate;

/**
 * @Description:
 */
public interface TypeTempService {
	public PageResult search(int pageNum, int pageSize, TbTypeTemplate typeTemplate);
	public void delete(Long[] ids);
	public void add(TbTypeTemplate typeTemplate);
	public TbTypeTemplate findOne(Long id);
	public void update(TbTypeTemplate typeTemplate);
}
