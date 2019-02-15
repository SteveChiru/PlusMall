package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbTypeTemplateMapper;
import com.plusmall.model.TbTypeTemplate;
import com.plusmall.model.TbTypeTemplateExample;
import com.plusmall.operator.TypeTempService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 */
@Service
public class TypeTempServiceImpl implements TypeTempService {
	private static Logger logger = Logger.getLogger(TypeTempServiceImpl.class);
	private static String logStr = "进入服务层-TypeTempServiceImpl-";

	@Autowired
	private TbTypeTemplateMapper typeTempMapper;

	@Override
	public PageResult search(int pageNum, int pageSize, TbTypeTemplate typeTemplate) {
		logger.info(logStr+"search方法");
		PageHelper.startPage(pageNum,pageSize);
		TbTypeTemplateExample example = new TbTypeTemplateExample();
		TbTypeTemplateExample.Criteria criteria = example.createCriteria();
		if (typeTemplate.getName() != null && typeTemplate.getName().length() > 0){
			criteria.andNameLike(typeTemplate.getName());
		}
		List<TbTypeTemplate> typeTempsList = typeTempMapper.selectByExample(example);
		Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) typeTempsList;
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException{
		logger.info(logStr+"delete方法");
		for (Long id: ids){
			typeTempMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void add(TbTypeTemplate typeTemplate) throws NullPointerException{
		logger.info(logStr+"save方法");
		typeTempMapper.insert(typeTemplate);
	}

	@Override
	public TbTypeTemplate findOne(Long id) {
		logger.info(logStr+"findOne方法");
		return typeTempMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbTypeTemplate typeTemplate) throws NullPointerException{
		logger.info(logStr+"update方法");
		typeTempMapper.updateByPrimaryKey(typeTemplate);
	}
}
