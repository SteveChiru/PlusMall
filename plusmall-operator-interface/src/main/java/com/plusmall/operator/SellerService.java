package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSeller;

/**
 * @Description:
 */
public interface SellerService {
	public PageResult search(int pageNum, int pageSize, TbSeller seller);
	public void delete(Long[] ids) throws NullPointerException;
	public void add(TbSeller seller) throws NullPointerException;
	public TbSeller findOne(String sellerId);
	public void udpate(TbSeller seller) throws NullPointerException;
	public void updateStatus(String strId,String status);
}
