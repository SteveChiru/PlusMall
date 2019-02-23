package com.plusmall.business.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.plusmall.business.TypeTemplateService;
import com.plusmall.mapper.TbSpecificationOptionMapper;
import com.plusmall.mapper.TbTypeTemplateMapper;
import com.plusmall.model.TbSpecificationOption;
import com.plusmall.model.TbSpecificationOptionExample;
import com.plusmall.model.TbTypeTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {
	private static Logger logger = Logger.getLogger(TypeTemplateServiceImpl.class);
	private static String logStr = "进入TypeTemplateServiceImpl-";

	@Autowired
	private TbTypeTemplateMapper typeTemplateMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;

	@Override
	public TbTypeTemplate findOne(Long id) {
		logger.info(logStr+"findOne方法");
		return typeTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Map> findSpecList(Long id) {
		logger.info(logStr+"进入findSpecList方法");
		TbTypeTemplate typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
		List<Map> list = JSON.parseArray(typeTemplate.getSpecIds(),Map.class);
		for (Map map: list){
			//查询规格选项列表
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			criteria.andSpecIdEqualTo(new Long((Integer)map.get("id")));
			List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
			map.put("options",options);
		}

		return list;
	}
}
