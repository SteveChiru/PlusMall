package com.plusmall.portal.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.mapper.TbContentMapper;
import com.plusmall.model.TbContent;
import com.plusmall.model.TbContentExample;
import com.plusmall.portal.ContentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Override
	public List<TbContent> search(Long catId) {
		logger.info(logStr+"search方法");
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		return contentMapper.selectByExample(example);
	}
}
