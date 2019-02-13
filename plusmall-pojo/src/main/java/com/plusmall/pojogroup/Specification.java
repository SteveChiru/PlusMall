package com.plusmall.pojogroup;

import com.plusmall.model.TbSpecification;
import com.plusmall.model.TbSpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 */
public class Specification implements Serializable {
	private TbSpecification specification;
	private List<TbSpecificationOption> specOptionsList;

	public TbSpecification getSpecification() {
		return specification;
	}

	public void setSpecification(TbSpecification specification) {
		this.specification = specification;
	}

	public List<TbSpecificationOption> getSpecOptionsList() {
		return specOptionsList;
	}

	public void setSpecOptionsList(List<TbSpecificationOption> specOptionsList) {
		this.specOptionsList = specOptionsList;
	}
}
