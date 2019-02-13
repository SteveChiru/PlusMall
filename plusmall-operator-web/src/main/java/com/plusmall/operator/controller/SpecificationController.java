package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSpecification;
import com.plusmall.operator.SpecificationService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {
	private static Logger logger = Logger.getLogger(SpecificationController.class);
	private static String logStr = "进入SpecificationController-";
	private static PageResult pageResult;
	private static ActionResult actionResult;

	@Reference
	private SpecificationService specService;

	@RequestMapping("/search")
	public PageResult searchSpecifications(int pageNum, int pageSize, @RequestBody TbSpecification specification){
		logger.info(logStr+"searchSpecifications方法");
		try {
			pageResult = specService.searchSpecifications(specification, pageNum, pageSize);
		}catch (NullPointerException e){
			e.printStackTrace();
		}
		return pageResult;
	}

	/**
	 * 新建specification时需要还需要添加specificationOption
	 * @param specification
	 * @return
	 * @throws NullPointerException
	 */
	@RequestMapping("/add")
	public ActionResult addSpec(@RequestBody TbSpecification specification) throws NullPointerException{
		logger.info(logStr+"addSpec方法");

		return actionResult;
	}
}
