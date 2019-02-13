package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbSpecificationOptionMapper;
import com.plusmall.model.TbSpecificationOption;
import com.plusmall.model.TbSpecificationOptionExample;
import com.plusmall.model.TbSpecificationOptionExample.Criteria;
import com.plusmall.operator.SpecOptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 */
@Service
public class SpecOptionServiceImpl implements SpecOptionService {
	private static Logger logger = Logger.getLogger(SpecOptionServiceImpl.class);
	private static String logStr = "进入SpecOptionServiceImpl-";

	@Autowired
	private TbSpecificationOptionMapper specOptionMapper;

	@Override
	public void add(TbSpecificationOption specOption) throws NullPointerException{
		logger.info(logStr+"add方法");
		specOptionMapper.insert(specOption);
	}

	@Override
	public TbSpecificationOption getSpecOptionById(Long id) throws NullPointerException{
		logger.info(logStr+"getSpecOptionById方法");
		TbSpecificationOption specOption = specOptionMapper.selectByPrimaryKey(id);
		return specOption;
	}

	@Override
	public void update(TbSpecificationOption specOption) throws NullPointerException{
		logger.info(logStr+"update方法");
		specOptionMapper.updateByPrimaryKey(specOption);
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException{
		logger.info(logStr+"delete方法");
		for (Long id: ids){
			specOptionMapper.deleteByPrimaryKey(id);
		}
	}

	/**
	 * 查找符合条件的规格选项并封装成PageResult返回
	 * @param specOption
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageResult searchSpecOptions(TbSpecificationOption specOption, int pageNum, int pageSize) {
		logger.info(logStr+"searchSpecOptions方法");
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		Criteria criteria = example.createCriteria();
		if (specOption.getSpecId() != null){
			criteria.andSpecIdEqualTo(specOption.getSpecId());
		}
		if (specOption.getOptionName() != null && specOption.getOptionName().length() > 0){
			criteria.andOptionNameLike("%"+specOption.getOptionName()+"%");
		}
		if (specOption.getId() != null){
			criteria.andIdEqualTo(specOption.getId());
		}
		if (specOption.getOrders() != null){
			criteria.andOrdersEqualTo(specOption.getOrders());
		}
		List<TbSpecificationOption> specOptionList = specOptionMapper.selectByExample(example);
		Page<TbSpecificationOption> page = (Page<TbSpecificationOption>) specOptionList;
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	/**
	 * 查找符合条件的规格选项
	 * @param specOption
	 * @return
	 */
	@Override
	public List<TbSpecificationOption> searchSpecOptions(TbSpecificationOption specOption) throws NullPointerException{
		logger.info(logStr+"searchSpecOptions方法");
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		Criteria criteria = example.createCriteria();
		if (specOption.getSpecId() != null){
			criteria.andSpecIdEqualTo(specOption.getSpecId());
		}
		if (specOption.getOptionName() != null && specOption.getOptionName().length() > 0){
			criteria.andOptionNameLike("%"+specOption.getOptionName()+"%");
		}
		if (specOption.getId() != null){
			criteria.andIdEqualTo(specOption.getId());
		}
		if (specOption.getOrders() != null){
			criteria.andOrdersEqualTo(specOption.getOrders());
		}
		List<TbSpecificationOption> specOptionList = specOptionMapper.selectByExample(example);
		return specOptionList;
	}
}
