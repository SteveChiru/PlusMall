package com.plusmall.order;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbOrder;
import com.plusmall.model.TbPayLog;

import java.util.List;

/**
 * @Description:
 */
public interface OrderService {
	public List<TbOrder> findAll();
	public PageResult findPage(int pageNum, int pageSize);
	public void add(TbOrder order);
	public void update(TbOrder order);
	public TbOrder findOne(Long id);
	public void delete(Long[] ids);
	public PageResult findPage(TbOrder order, int pageNum, int pageSize);
	public TbPayLog searchPayLogFromRedis(String userId);
	public void updateOrderStatus(String out_trade_no,String transaction_id);
}
