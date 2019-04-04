package com.plusmall.seckill.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbSeckillGoodsMapper;
import com.plusmall.model.TbSeckillGoods;
import com.plusmall.model.TbSeckillGoodsExample;
import com.plusmall.seckill.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 */
@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private TbSeckillGoodsMapper seckillGoodsMapper;

	@Override
	public List<TbSeckillGoods> findAll() {
		return seckillGoodsMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbSeckillGoods> page=   (Page<TbSeckillGoods>) seckillGoodsMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public void add(TbSeckillGoods seckillGoods) {
		seckillGoodsMapper.insert(seckillGoods);
	}

	@Override
	public void update(TbSeckillGoods seckillGoods) {
		seckillGoodsMapper.updateByPrimaryKey(seckillGoods);
	}

	@Override
	public TbSeckillGoods findOne(Long id) {
		return seckillGoodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			seckillGoodsMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public PageResult findPage(TbSeckillGoods seckillGoods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		TbSeckillGoodsExample example=new TbSeckillGoodsExample();
		TbSeckillGoodsExample.Criteria criteria = example.createCriteria();

		if(seckillGoods!=null){
			if(seckillGoods.getTitle()!=null && seckillGoods.getTitle().length()>0){
				criteria.andTitleLike("%"+seckillGoods.getTitle()+"%");
			}
			if(seckillGoods.getSmallPic()!=null && seckillGoods.getSmallPic().length()>0){
				criteria.andSmallPicLike("%"+seckillGoods.getSmallPic()+"%");
			}
			if(seckillGoods.getSellerId()!=null && seckillGoods.getSellerId().length()>0){
				criteria.andSellerIdLike("%"+seckillGoods.getSellerId()+"%");
			}
			if(seckillGoods.getStatus()!=null && seckillGoods.getStatus().length()>0){
				criteria.andStatusLike("%"+seckillGoods.getStatus()+"%");
			}
			if(seckillGoods.getIntroduction()!=null && seckillGoods.getIntroduction().length()>0){
				criteria.andIntroductionLike("%"+seckillGoods.getIntroduction()+"%");
			}

		}

		Page<TbSeckillGoods> page= (Page<TbSeckillGoods>)seckillGoodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<TbSeckillGoods> findList() {
		//获取秒杀商品列表
		List<TbSeckillGoods> seckillGoodsList = redisTemplate.boundHashOps("seckillGoods").values();
		if(seckillGoodsList==null || seckillGoodsList.size()==0){
			//如果缓存中没有，就把数据库中的秒杀表格中的商品信息加载到缓存中
			TbSeckillGoodsExample example=new TbSeckillGoodsExample();
			TbSeckillGoodsExample.Criteria criteria = example.createCriteria();
			criteria.andStatusEqualTo("1");//审核通过
			criteria.andStockCountGreaterThan(0);//剩余库存大于0
			criteria.andStartTimeLessThanOrEqualTo(new Date());//开始时间小于等于当前时间
			criteria.andEndTimeGreaterThan(new Date());//结束时间大于当前时间
			seckillGoodsList = seckillGoodsMapper.selectByExample(example);
			//将商品列表装入缓存
			System.out.println("将秒杀商品列表装入缓存");
			for (TbSeckillGoods seckillGoods:seckillGoodsList){
				redisTemplate.boundHashOps("seckillGoods").put(seckillGoods.getId(),seckillGoods);
			}
		}
		return seckillGoodsList;
	}

	@Override
	public TbSeckillGoods findOneFromRedis(Long id) {
		return (TbSeckillGoods)redisTemplate.boundHashOps("seckillGoods").get(id);
	}
}
