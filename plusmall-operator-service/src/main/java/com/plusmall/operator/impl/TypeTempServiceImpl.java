package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbSpecificationOptionMapper;
import com.plusmall.mapper.TbTypeTemplateMapper;
import com.plusmall.model.TbSpecificationOption;
import com.plusmall.model.TbSpecificationOptionExample;
import com.plusmall.model.TbTypeTemplate;
import com.plusmall.model.TbTypeTemplateExample;
import com.plusmall.operator.TypeTempService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Service(timeout = 120000)
public class TypeTempServiceImpl implements TypeTempService {
	private static Logger logger = Logger.getLogger(TypeTempServiceImpl.class);
	private static String logStr = "进入服务层-TypeTempServiceImpl-";

	@Autowired
	private TbTypeTemplateMapper typeTempMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

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
		saveToRedis();//存入数据到缓存
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
		logger.info(logStr+"add方法");
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

	@Override
	public List<TbTypeTemplate> findAll() {
		logger.info(logStr+"findAll方法");
		return typeTempMapper.selectByExample(null);
	}

	@Override
	public List<Map> findSpecList(Long id) {
		logger.info(logStr+"进入findSpecList方法");
		TbTypeTemplate typeTemplate = typeTempMapper.selectByPrimaryKey(id);
		List<Map> list = JSON.parseArray(typeTemplate.getSpecIds(),Map.class);
		for (Map map: list){
			//查询规格选项列表
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(new Long((Integer)map.get("id")));
			List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
			map.put("options",options);
		}

		return list;
	}

	private void saveToRedis(){
		logger.info(logStr+"saveToRedis方法");
		//获取模板数据
		List<TbTypeTemplate> typeTemplateList = findAll();
		//循环模板
		for(TbTypeTemplate typeTemplate :typeTemplateList){
			//存储品牌列表
			List<Map> brandList = JSON.parseArray(typeTemplate.getBrandIds(), Map.class);
			redisTemplate.boundHashOps("brandList").put(typeTemplate.getId(), brandList);
			//存储规格列表
			List<Map> specList = findSpecList(typeTemplate.getId());//根据模板ID查询规格列表
			redisTemplate.boundHashOps("specList").put(typeTemplate.getId(), specList);
		}

	}
}
