package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbBrandMapper;
import com.plusmall.model.TbBrand;
import com.plusmall.model.TbBrandExample;
import com.plusmall.model.TbBrandExample.Criteria;
import com.plusmall.operator.BrandService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 */
@Service
public class BrandServiceImpl implements BrandService {

	private static Logger logger = Logger.getLogger(BrandServiceImpl.class);

	@Autowired
	private TbBrandMapper brandMapper;

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

	@Override
	public void delete(Long[] ids) {
		logger.info("进入服务层-delete方法");
		for (Long id : ids){
			brandMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public PageResult searchBrands(TbBrand brandinfo, int pageNum, int pageSize) {
		logger.info("进入服务层-searchBrands方法");
		PageHelper.startPage(pageNum,pageSize);
		TbBrandExample example = new TbBrandExample();
		Criteria criteria = example.createCriteria();
		if (brandinfo != null){
			if (brandinfo.getName() != null && brandinfo.getName().length() > 0){
				criteria.andNameLike("%"+brandinfo.getName()+"%");
			}
			if (brandinfo.getFirstChar() != null && brandinfo.getFirstChar().length() >0){
				criteria.andFirstCharEqualTo(brandinfo.getFirstChar());
			}
		}
		Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}
}
