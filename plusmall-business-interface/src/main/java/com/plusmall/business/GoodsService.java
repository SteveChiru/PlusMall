package com.plusmall.business;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbGoods;
import com.plusmall.pojogroup.Goods;

/**
 * @Description:
 */
public interface GoodsService {
	public void add(Goods goods) throws NullPointerException;
	public PageResult search(int pageNum, int pageSize, TbGoods tbGoods);
}
