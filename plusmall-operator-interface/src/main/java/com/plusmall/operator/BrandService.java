package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbBrand;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
public interface BrandService {
	public void add(TbBrand brand);
	public TbBrand getBrandById(Long id);
	public void update(TbBrand brand);
	public void delete(Long[] ids);
	public PageResult searchBrands(TbBrand brandinfo,int pageNum,int pageSize);
	public List<Map> selectOptionList();
}
