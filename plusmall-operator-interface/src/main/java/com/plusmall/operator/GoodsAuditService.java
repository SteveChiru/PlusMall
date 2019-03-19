package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbGoods;
import com.plusmall.model.TbItem;

import java.util.List;

/**
 * @Description:
 */
public interface GoodsAuditService {
	public PageResult search(int pageNum, int pageSize, TbGoods tbGoods);
	public void updateStatus(Long[] ids,String status) throws NullPointerException;
	public void delete(Long[] ids) throws NullPointerException;
	public List<TbItem> findItemListByGoodsIdAndStatus(Long[] goodsIds, String status ) throws NullPointerException;
}
