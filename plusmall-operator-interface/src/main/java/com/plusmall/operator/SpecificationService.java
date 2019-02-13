package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSpecification;
import com.plusmall.pojogroup.Specification;

/**
 * @Description:
 */
public interface SpecificationService {
	public void add(TbSpecification specification);
	public Specification getSpecificationById(Long id);
	public void update(Specification specification);
	public void delete(Long[] ids);
	public PageResult searchSpecifications(TbSpecification specification, int pageNum, int pageSize);
}
