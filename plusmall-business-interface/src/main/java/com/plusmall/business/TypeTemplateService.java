package com.plusmall.business;

import com.plusmall.model.TbTypeTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
public interface TypeTemplateService {
	public TbTypeTemplate findOne(Long id);
	public List<Map> findSpecList(Long id);
}
