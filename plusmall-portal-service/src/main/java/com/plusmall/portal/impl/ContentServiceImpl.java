package com.plusmall.portal.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.mapper.TbContentMapper;
import com.plusmall.model.TbContent;
import com.plusmall.model.TbContentExample;
import com.plusmall.portal.ContentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @Description:
 */
@Service
public class ContentServiceImpl implements ContentService {
	private static Logger logger = Logger.getLogger(ContentServiceImpl.class);
	private static String logStr = "进入ContentServiceImpl-";

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public List<TbContent> search(Long catId) {
		logger.info(logStr+"search方法");
		//Map<String,Map<string,List>>
		List<TbContent> contentList = (List<TbContent>) redisTemplate.boundHashOps("content").get(catId);
		if (contentList == null){
			logger.info("从数据库读取数据放入缓存");
			TbContentExample example = new TbContentExample();
			TbContentExample.Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(catId);
			criteria.andStatusEqualTo("1");	//开启状态
			contentList = contentMapper.selectByExample(example);
			redisTemplate.boundHashOps("content").put(catId,contentList);
		}else {
			logger.info("从缓存读取数据");
		}

		return contentList;
	}
}
