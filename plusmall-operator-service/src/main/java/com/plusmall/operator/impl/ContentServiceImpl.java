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
			contentMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void add(TbContent content) throws NullPointerException {
		logger.info(logStr+"add方法");
		contentMapper.insert(content);
	}

	@Override
	public TbContent findOne(Long id) {
		logger.info(logStr+"findOne方法");
		return contentMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbContent content) throws NullPointerException {
		logger.info(logStr+"update方法");
		contentMapper.updateByPrimaryKey(content);
	}
}
