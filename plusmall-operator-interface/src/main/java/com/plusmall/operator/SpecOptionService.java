package com.plusmall.operator;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSpecificationOption;

import java.util.List;

/**
 * @Description:
 */
public interface SpecOptionService {
	public void add(TbSpecificationOption specOption);
	public TbSpecificationOption getSpecOptionById(Long id);
	public void update(TbSpecificationOption specOption);
	public void delete(Long[] ids);
	public PageResult searchSpecOptions(TbSpecificationOption specOption, int pageNum, int pageSize);
	public List<TbSpecificationOption> searchSpecOptions(TbSpecificationOption specOption);
}
