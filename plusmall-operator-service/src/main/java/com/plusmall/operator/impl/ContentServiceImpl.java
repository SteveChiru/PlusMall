package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbContentMapper;
import com.plusmall.model.TbContent;
import com.plusmall.operator.ContentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @Description:
 */
@Service
public class ContentServiceImpl implements ContentService {
	private static Logger logger = Logger.getLogger(ContentCatServiceImpl.class);
	private static String logStr = "进入ContentServiceImpl=";

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public PageResult search(int pageNum, int pageSize) {
		logger.info(logStr+"search方法");
		PageHelper.startPage(pageNum,pageSize);
		Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(null);
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {
		logger.info(logStr+"delete方法");
		for (Long id : ids){
			//删除广告后要清理一下redis缓存
			Long categoryId = contentMapper.selectByPrimaryKey(id).getCategoryId();
			redisTemplate.boundHashOps("content").delete(categoryId);
			contentMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void add(TbContent content) throws NullPointerException {
		logger.info(logStr+"add方法");
		contentMapper.insert(content);
		//新增广告后清除一下缓存
		redisTemplate.boundHashOps("content").delete(content.getCategoryId());
	}

	@Override
	public TbContent findOne(Long id) {
		logger.info(logStr+"findOne方法");
		return contentMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbContent content) throws NullPointerException {
		logger.info(logStr+"update方法");
		//如果修改了广告的类型，那么修改前和修改后广告类型的缓存都需要清理下
		//修改前清理下缓存
		Long catIdOrigin = contentMapper.selectByPrimaryKey(content.getId()).getCategoryId();
		redisTemplate.boundHashOps("content").delete(catIdOrigin);
		contentMapper.updateByPrimaryKey(content);
		//更新完广告后再判断分类ID是否发生了更改
		if (catIdOrigin.longValue() != content.getCategoryId().longValue()){
			redisTemplate.boundHashOps("content").delete(content.getCategoryId());
		}

	}
}
