package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbSpecificationMapper;
import com.plusmall.mapper.TbSpecificationOptionMapper;
import com.plusmall.model.TbSpecification;
import com.plusmall.model.TbSpecificationExample;
import com.plusmall.model.TbSpecificationOption;
import com.plusmall.model.TbSpecificationOptionExample;
import com.plusmall.operator.SpecificationService;
import com.plusmall.pojogroup.Specification;
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
	@Autowired
	private TbSpecificationOptionMapper specOptionMapper;

	@Override
	public void add(Specification spec) throws NullPointerException{
		logger.info(logStr+"add方法");
		specificationMapper.insert(spec.getSpecification());

		TbSpecificationExample example = new TbSpecificationExample();
		TbSpecificationExample.Criteria criteria = example.createCriteria();
		criteria.andSpecNameEqualTo(spec.getSpecification().getSpecName());
		List<TbSpecification> tbSpecifications = specificationMapper.selectByExample(example);
		//规格唯一时，才能插入规格选项
		if (tbSpecifications.size() == 1){
			Long specId = tbSpecifications.get(0).getId();
			for (TbSpecificationOption specOption: spec.getSpecOptionsList()){
				specOption.setSpecId(specId);
				specOptionMapper.insert(specOption);
			}
		}
	}

	@Override
	public Specification getSpecificationById(Long id) {
		logger.info(logStr+"getSpecificationById方法");
		Specification spec = new Specification();
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		spec.setSpecification(tbSpecification);
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(id);
		List<TbSpecificationOption> specOptionsList = specOptionMapper.selectByExample(example);
		spec.setSpecOptionsList(specOptionsList);
		return spec;
	}

	@Override
	public void update(Specification spec) throws NullPointerException{
		logger.info(logStr+"update方法");
		specificationMapper.updateByPrimaryKey(spec.getSpecification());
		//更新spec的方法：先删除，再插入
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		criteria.andSpecIdEqualTo(spec.getSpecification().getId());
		specOptionMapper.deleteByExample(example);

		for (TbSpecificationOption specOption: spec.getSpecOptionsList()){
			specOptionMapper.insert(specOption);
		}
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {
		logger.info(logStr+"delete方法");
		for (Long id : ids){
			specificationMapper.deleteByPrimaryKey(id);
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specOptionMapper.deleteByExample(example);
		}
	}

	@Override
	public PageResult searchSpecifications(TbSpecification specification, int pageNum, int pageSize) throws NullPointerException{
		logger.info(logStr+"searchSpecifications");
		//用PageHelper插件将查询结果封装成page
		PageHelper.startPage(pageNum,pageSize);
		TbSpecificationExample example = new TbSpecificationExample();
		TbSpecificationExample.Criteria criteria = example.createCriteria();
		if (specification.getSpecName() != null && specification.getSpecName().length() > 0){
			criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
		}
		List<TbSpecification> tbSpecifications = specificationMapper.selectByExample(example);
		Page<TbSpecification> page = (Page<TbSpecification>) tbSpecifications;
		//将Page对象封装成PageResult对象返回
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}
}
