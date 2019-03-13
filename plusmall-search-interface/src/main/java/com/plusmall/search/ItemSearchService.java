package com.plusmall.search;

import java.util.Map;

/**
 * @Description:
 */
public interface ItemSearchService {
	public Map<String,Object> search(Map searchMap) throws NullPointerException;
}
