package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSpecification;
import com.plusmall.model.TbSpecificationOption;
import com.plusmall.operator.SpecOptionService;
import com.plusmall.operator.SpecificationService;
import com.plusmall.pojogroup.Specification;
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
	public ActionResult addSpec(@RequestBody Specification spec){
		logger.info(logStr+"addSpec方法");
		try {
			//添加spec
			specService.add(spec);	//此处应该添加事务处理：规格和规格选项都添加成功才算成功
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

	@RequestMapping("/findOne")
	public Specification findOne(Long id){
		logger.info(logStr+"findOne方法");
		Specification spec = specService.getSpecificationById(id);
		return spec;
	}

	@RequestMapping("/update")
	public ActionResult updateSpec(@RequestBody Specification spec){
		logger.info(logStr+"updateSpec方法");
		try {
			specService.update(spec);
			actionResult = new ActionResult(true,"更新规格成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新规格失败");
		}
		return actionResult;
	}
}
