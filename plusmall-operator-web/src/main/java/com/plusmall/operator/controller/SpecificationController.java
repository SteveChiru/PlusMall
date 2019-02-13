package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSpecification;
import com.plusmall.model.TbSpecificationOption;
import com.plusmall.operator.SpecOptionService;
import com.plusmall.operator.SpecificationService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	@Reference
	private SpecOptionService specOptionService;

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

	@RequestMapping("/add")
	public ActionResult addSpec(@RequestBody TbSpecification spec, List<TbSpecificationOption> specOptionList){
		logger.info(logStr+"addSpec方法");
		try {
			//添加spec
			specService.add(spec);
			//添加specOption
			for (TbSpecificationOption specOption: specOptionList){
				specOptionService.add(specOption);
			}
			actionResult = new ActionResult(true,"添加规格成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"添加规格失败");
		}
		return actionResult;
	}

	@RequestMapping("/delete")
	public ActionResult deleteSpecs(Long[] ids){
		logger.info(logStr+"deleteSpecs方法");
		try {
			specService.delete(ids);
			actionResult = new ActionResult(true,"删除规格失败");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"删除规格失败");
		}
		return actionResult;
	}
}
