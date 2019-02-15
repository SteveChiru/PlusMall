package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSpecification;
import com.plusmall.pojogroup.Specification;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
public interface SpecificationService {
	public void add(Specification specification);
	public Specification getSpecificationById(Long id);
	public void update(Specification specification);
	public void delete(Long[] ids);
	public PageResult searchSpecifications(TbSpecification specification, int pageNum, int pageSize);
	public List<Map> selectOptionList();
}
