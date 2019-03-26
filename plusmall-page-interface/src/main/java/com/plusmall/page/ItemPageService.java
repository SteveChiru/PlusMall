package com.plusmall.page;

/**
 * @Description:
 */
public interface ItemPageService {
	public boolean genItemHtml(Long goodsId);
	public boolean deleteItemHtml(Long[] goodsIds);
}
