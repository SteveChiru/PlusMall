package com.plusmall.business;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSeller;

/**
 * @Description:
 */
public interface SellerService {
	public PageResult search(int pageNum, int pageSize, TbSeller seller) throws NullPointerException;
	public void delete(Long[] ids) throws NullPointerException;
	public void add(TbSeller seller) throws NullPointerException;
	public TbSeller findOne(String sellerId);
	public void update(TbSeller seller) throws NullPointerException;
}
