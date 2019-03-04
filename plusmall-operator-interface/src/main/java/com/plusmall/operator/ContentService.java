package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbContent;

/**
 * @Description:
 */
public interface ContentService {
	public PageResult search(int pageNum, int pageSize);
	public void delete(Long[] ids) throws NullPointerException;
	public void add(TbContent content) throws NullPointerException;
	public TbContent findOne(Long id);
	public void update(TbContent content) throws NullPointerException;
}
