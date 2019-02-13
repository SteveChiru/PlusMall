package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSpecification;

/**
 * @Description:
 */
public interface SpecificationService {
	public void add(TbSpecification specification);
	public TbSpecification getSpecificationById(Long id);
	public void update(TbSpecification specification);
	public void delete(Long[] ids);
	public PageResult searchSpecifications(TbSpecification specification, int pageNum, int pageSize);
}
