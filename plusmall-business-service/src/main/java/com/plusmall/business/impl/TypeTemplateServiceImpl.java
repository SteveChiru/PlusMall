package com.plusmall.business.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.business.TypeTemplateService;
import com.plusmall.mapper.TbTypeTemplateMapper;
import com.plusmall.model.TbTypeTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {
	private static Logger logger = Logger.getLogger(TypeTemplateServiceImpl.class);
	private static String logStr = "进入TypeTemplateServiceImpl-";

	@Autowired
	private TbTypeTemplateMapper typeTemplateMapper;

	@Override
	public TbTypeTemplate findOne(Long id) {
		logger.info(logStr+"findOne方法");
		return typeTemplateMapper.selectByPrimaryKey(id);
	}
}
