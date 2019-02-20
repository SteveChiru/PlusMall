package com.plusmall.business.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.business.ItemCatService;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbItemCatMapper;
import com.plusmall.model.TbItemCat;
import com.plusmall.model.TbItemCatExample;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	private static Logger logger = Logger.getLogger(ItemCatServiceImpl.class);
	private static String logStr = "进入ItemCatServiceImpl-";

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public PageResult searchByParentId(Long parentId) {
		logger.info(logStr+"searchByParentId方法");
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
		return new PageResult((long) tbItemCats.size(),tbItemCats);
	}

	@Override
	public TbItemCat findOne(Long id) {
		logger.info(logStr+"findOne方法");
		return itemCatMapper.selectByPrimaryKey(id);
	}
}
