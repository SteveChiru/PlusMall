package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbBrand;

/**
 * @Description:
 */
public interface Brand {
	public PageResult getBrandsPerPae(int pageNum, int pageSize);
	public void add(TbBrand brand);
	public TbBrand getBrandById(Long id);
	public void update(TbBrand brand);
}
