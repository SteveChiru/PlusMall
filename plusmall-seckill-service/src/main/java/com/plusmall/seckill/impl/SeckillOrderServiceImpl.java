package com.plusmall.seckill.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.IdWorker;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbSeckillGoodsMapper;
import com.plusmall.mapper.TbSeckillOrderMapper;
import com.plusmall.model.TbSeckillGoods;
import com.plusmall.model.TbSeckillOrder;
import com.plusmall.model.TbSeckillOrderExample;
import com.plusmall.seckill.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 */
@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private IdWorker idWorker;

	@Autowired
	private TbSeckillOrderMapper seckillOrderMapper;

	@Autowired
	private TbSeckillGoodsMapper seckillGoodsMapper;

	@Override
	public List<TbSeckillOrder> findAll() {
		return seckillOrderMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbSeckillOrder> page=   (Page<TbSeckillOrder>) seckillOrderMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void add(TbSeckillOrder seckillOrder) {
		seckillOrderMapper.insert(seckillOrder);
	}

	@Override
	public void update(TbSeckillOrder seckillOrder) {
		seckillOrderMapper.updateByPrimaryKey(seckillOrder);
	}

	@Override
	public TbSeckillOrder findOne(Long id) {
		return seckillOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			seckillOrderMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public PageResult findPage(TbSeckillOrder seckillOrder, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbSeckillOrderExample example=new TbSeckillOrderExample();
		TbSeckillOrderExample.Criteria criteria = example.createCriteria();

		if(seckillOrder!=null){
			if(seckillOrder.getUserId()!=null && seckillOrder.getUserId().length()>0){
				criteria.andUserIdLike("%"+seckillOrder.getUserId()+"%");
			}
			if(seckillOrder.getSellerId()!=null && seckillOrder.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+seckillOrder.getSellerId()+"%");
			}
			if(seckillOrder.getStatus()!=null && seckillOrder.getStatus().length()>0){
				criteria.andStatusLike("%"+seckillOrder.getStatus()+"%");
			}
			if(seckillOrder.getReceiverAddress()!=null && seckillOrder.getReceiverAddress().length()>0){
				criteria.andReceiverAddressLike("%"+seckillOrder.getReceiverAddress()+"%");
			}
			if(seckillOrder.getReceiverMobile()!=null && seckillOrder.getReceiverMobile().length()>0){
				criteria.andReceiverMobileLike("%"+seckillOrder.getReceiverMobile()+"%");
			}
			if(seckillOrder.getReceiver()!=null && seckillOrder.getReceiver().length()>0){
				criteria.andReceiverLike("%"+seckillOrder.getReceiver()+"%");
			}
			if(seckillOrder.getTransactionId()!=null && seckillOrder.getTransactionId().length()>0){
				criteria.andTransactionIdLike("%"+seckillOrder.getTransactionId()+"%");
			}

		}

		Page<TbSeckillOrder> page= (Page<TbSeckillOrder>)seckillOrderMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void submitOrder(Long seckillId, String userId) {
		//从缓存中查询秒杀商品
		TbSeckillGoods seckillGoods = (TbSeckillGoods)redisTemplate.boundHashOps("seckillGoods").get(seckillId);
		if(seckillGoods==null){
			throw new RuntimeException("商品不存在");
		}
		if(seckillGoods.getStockCount()<=0){
			throw new RuntimeException("商品已抢购一空");
		}
		//扣减(redis)库存
		seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
		redisTemplate.boundHashOps("seckillGoods").put(seckillId,seckillGoods);//放回缓存
		if(seckillGoods.getStockCount()==0){//如果已经被秒光
			seckillGoodsMapper.updateByPrimaryKey(seckillGoods); //同步到数据库
			redisTemplate.boundHashOps("seckillGoods").delete(seckillId);
		}

		//保存(redis)订单
		long orderId = idWorker.nextId();
		TbSeckillOrder seckillOrder=new TbSeckillOrder();
		seckillOrder.setId(orderId);
		seckillOrder.setCreateTime(new Date());
		seckillOrder.setMoney(seckillGoods.getCostPrice());//秒杀价格
		seckillOrder.setSeckillId(seckillId);
		seckillOrder.setSellerId(seckillGoods.getSellerId());
		seckillOrder.setUserId(userId);//设置用户ID
		seckillOrder.setStatus("0");//状态
		redisTemplate.boundHashOps("seckillOrder").put(userId, seckillOrder);
	}

	@Override
	public TbSeckillOrder searchOrderFromRedisByUserId(String userId) {
		return (TbSeckillOrder)redisTemplate.boundHashOps("seckillOrder").get(userId);
	}

	@Override
	public void saveOrderFromRedisToDb(String userId, Long orderId, String transactionId) {
		System.out.println("saveOrderFromRedisToDb:"+userId);
		//根据用户ID查询日志
		TbSeckillOrder seckillOrder = (TbSeckillOrder) redisTemplate.boundHashOps("seckillOrder").get(userId);
		if(seckillOrder==null){
			throw new RuntimeException("订单不存在");
		}
		//如果与传递过来的订单号不符
		if(seckillOrder.getId().longValue()!=orderId.longValue()){
			throw new RuntimeException("订单不相符");
		}
		seckillOrder.setTransactionId(transactionId);//交易流水号
		seckillOrder.setPayTime(new Date());//支付时间
		seckillOrder.setStatus("1");//状态
		seckillOrderMapper.insert(seckillOrder);//保存到数据库
		redisTemplate.boundHashOps("seckillOrder").delete(userId);//从redis中清除
	}
}
