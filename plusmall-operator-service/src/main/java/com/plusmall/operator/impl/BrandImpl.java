package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbBrandMapper;
import com.plusmall.model.TbBrand;
import com.plusmall.model.TbBrandExample;
import com.plusmall.operator.Brand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 */
@Service
public class BrandImpl implements Brand {

	private static Logger logger = Logger.getLogger(BrandImpl.class);

	@Autowired
	private TbBrandMapper brandMapper;

	@Override
	public PageResult getBrandsPerPae(int pageNum, int pageSize) {
		logger.info("进入服务层-getBrandsPerPage方法");
		PageHelper.startPage(pageNum,pageSize);
		Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	@Override
	public void add(TbBrand brand) {
		logger.info("进入服务层-add方法");
		brandMapper.insert(brand);
	}

	@Override
	public TbBrand getBrandById(Long id) {
		logger.info("进入服务层-getBrandById方法");
		TbBrand brand = brandMapper.selectByPrimaryKey(id);
		return brand;
	}

	@Override
	public void update(TbBrand brand) {
		logger.info("进入服务层-update方法");
		brandMapper.updateByPrimaryKey(brand);
	}
}
