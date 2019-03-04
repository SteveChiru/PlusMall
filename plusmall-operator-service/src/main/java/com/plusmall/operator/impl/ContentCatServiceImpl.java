package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbContentCategoryMapper;
import com.plusmall.model.TbContentCategory;
import com.plusmall.operator.ContentCatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {
	private static Logger logger = Logger.getLogger(ContentCatServiceImpl.class);
	private static String logStr = "进入ContentCatServiceImpl-";

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public PageResult search(int pageNum, int pageSize) {
		logger.info(logStr+"search方法");
		PageHelper.startPage(pageNum,pageSize);
		Page<TbContentCategory> page = (Page<TbContentCategory>) contentCategoryMapper.selectByExample(null);
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {
		logger.info(logStr+"delete方法");
		for (Long id : ids){
			contentCategoryMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void add(TbContentCategory contentCategory) throws NullPointerException {
		logger.info(logStr+"add方法");
		contentCategoryMapper.insert(contentCategory);
	}

	@Override
	public TbContentCategory findOne(Long id) {
		logger.info(logStr+"findOne方法");
		return contentCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbContentCategory contentCategory) throws NullPointerException {
		logger.info(logStr+"update方法");
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
	}
}
