package com.plusmall.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.business.TypeTemplateService;
import com.plusmall.model.TbTypeTemplate;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
	private static Logger logger = Logger.getLogger(TypeTemplateController.class);
	private static String logStr = "进入TypeTemplateController-";

	@Reference
	private TypeTemplateService typeTemplateService;

	@RequestMapping("/findOne")
	public TbTypeTemplate findOne(Long id){
		logger.info(logStr+"findOne方法");
		return typeTemplateService.findOne(id);
	}

	@RequestMapping("findSpecList")
	public List<Map> findSpecList(Long id){
		logger.info(logStr+"findSpecList方法");
		return typeTemplateService.findSpecList(id);
	}
}
