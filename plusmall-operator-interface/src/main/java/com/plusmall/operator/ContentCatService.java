package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbContentCategory;

import java.util.List;

/**
 * @Description:
 */
public interface ContentCatService {
	public PageResult search(int pageNum,int pageSize);
	public void delete(Long[] ids) throws NullPointerException;
	public void add(TbContentCategory contentCategory) throws NullPointerException;
	public TbContentCategory findOne(Long id);
	public void update(TbContentCategory contentCategory) throws NullPointerException;
	public List<TbContentCategory> findAll();
}
