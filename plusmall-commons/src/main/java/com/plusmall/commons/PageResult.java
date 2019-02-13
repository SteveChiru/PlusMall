package com.plusmall.commons;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 */
public class PageResult implements Serializable {
	private Long total;
	private Integer pages;
	private Integer size;
	private List result;

	public PageResult() {
	}

	public PageResult(Long total, Integer pages, Integer size, List result) {
		this.total = total;
		this.pages = pages;
		this.size = size;
		this.result = result;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
}
