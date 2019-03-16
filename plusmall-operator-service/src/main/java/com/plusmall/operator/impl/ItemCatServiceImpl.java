package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbItemCatMapper;
import com.plusmall.model.TbItemCat;
import com.plusmall.model.TbItemCatExample;
import com.plusmall.operator.ItemCatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @Description:
 */
@Service(timeout = 600000)
public class ItemCatServiceImpl implements ItemCatService {
	private static Logger logger = Logger.getLogger(ItemCatServiceImpl.class);
	private static String logStr = "进入ItemCatServiceImpl-";

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public PageResult searchByParentId(int pageNum, int pageSize, Long parentId) {
		logger.info(logStr+"searchByParentId方法");
		PageHelper.startPage(pageNum,pageSize);
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(example);

		//每次执行查询的时候，一次性读取缓存进行存储 (因为每次增删改都要执行此方法)
		List<TbItemCat> list = findAll();
		for(TbItemCat itemCat:list){
			redisTemplate.boundHashOps("itemCat").put(itemCat.getName(), itemCat.getTypeId());
		}
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {
		logger.info(logStr+"delete方法");
		for (Long id : ids){
			itemCatMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void add(TbItemCat itemCat) throws NullPointerException {
		logger.info(logStr+"add方法");
		itemCatMapper.insert(itemCat);
	}

	@Override
	public TbItemCat findOne(Long id) {
		logger.info(logStr+"findOne方法");
		return itemCatMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbItemCat itemCat) throws NullPointerException {
		logger.info(logStr+"update方法");
		itemCatMapper.updateByPrimaryKey(itemCat);
	}

	@Override
	public List<TbItemCat> findAll() {
		logger.info(logStr+"findAll方法");
		return itemCatMapper.selectByExample(null);
	}
}
