package com.plusmall.portal;

import com.plusmall.model.TbContent;

import java.util.List;

/**
 * @Description:
 */
public interface ContentService {
	public List<TbContent> search(Long catId);
}
