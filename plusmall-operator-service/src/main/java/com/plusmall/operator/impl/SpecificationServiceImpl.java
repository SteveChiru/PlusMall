package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbSpecificationMapper;
import com.plusmall.mapper.TbSpecificationOptionMapper;
import com.plusmall.model.TbSpecification;
import com.plusmall.model.TbSpecificationExample;
import com.plusmall.model.TbSpecificationExample.Criteria;
import com.plusmall.model.TbSpecificationOption;
import com.plusmall.operator.SpecOptionService;
import com.plusmall.operator.SpecificationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {
	private static Logger logger = Logger.getLogger(SpecificationServiceImpl.class);
	private static String logStr = "进入服务层-SpecificationServiceImpl类中-";

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Reference
	private SpecOptionService specOptionService;

	@Override
	public void add(TbSpecification specification) throws NullPointerException{
		logger.info(logStr+"add方法");
		specificationMapper.insert(specification);
	}

	@Override
	public TbSpecification getSpecificationById(Long id) throws NullPointerException{
		logger.info(logStr+"getSpecificationById方法");
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		return tbSpecification;
	}

	@Override
	public void update(TbSpecification specification) throws NullPointerException{
		logger.info(logStr+"update方法");
		specificationMapper.updateByPrimaryKey(specification);
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {
		logger.info(logStr+"delete方法");
		for (Long id : ids){
			specificationMapper.deleteByPrimaryKey(id);
			//删除规格选项
			//1.根据specid找到specOptions
			TbSpecificationOption specOptionQuery = new TbSpecificationOption();
			specOptionQuery.setSpecId(id);
			List<TbSpecificationOption> specOptionsToDeleteList = specOptionService.searchSpecOptions(specOptionQuery);
			List<Long> idsToDeleteList = new ArrayList<>();
			for (TbSpecificationOption specOption: specOptionsToDeleteList){
				idsToDeleteList.add(specOption.getId());
			}
			Long[] idArray = new Long[idsToDeleteList.size()];
			specOptionService.delete(idArray);
		}
	}

	@Override
	public PageResult searchSpecifications(TbSpecification specification, int pageNum, int pageSize) throws NullPointerException{
		logger.info(logStr+"searchSpecifications");
		TbSpecificationExample example = new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		if (specification.getSpecName() != null && specification.getSpecName().length() > 0){
			criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
		}
		List<TbSpecification> tbSpecifications = specificationMapper.selectByExample(example);
		//用PageHelper插件将查询结果封装成page
		PageHelper.startPage(pageNum,pageSize);
		Page<TbSpecification> page = (Page<TbSpecification>) tbSpecifications;
		//将Page对象封装成PageResult对象返回
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}
}
