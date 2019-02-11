package com.plusmall.operator;

import com.plusmall.commons.PageResult;

/**
 * @Description:
 */
public interface Brand {
	public PageResult getBrandsPerPae(int pageNum, int pageSize);
}
