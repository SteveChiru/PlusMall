package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbTypeTemplate;
import com.plusmall.operator.TypeTempService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description:
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTempController {
	private static Logger logger = Logger.getLogger(TypeTempController.class);
	private static String logStr = "进入TypeTempController-";
	private static ActionResult actionResult;
	private static PageResult pageResult;

	@Reference
	private TypeTempService typeTempService;

	@RequestMapping("/search")
	public PageResult search(int pageNum,int pageSize,@RequestBody TbTypeTemplate typeTemplate){
		logger.info(logStr+"search方法");
		return typeTempService.search(pageNum, pageSize, typeTemplate);
	}

	@RequestMapping("/delete")
	public ActionResult delete(Long[] ids){
		logger.info(logStr+"delete方法");
		try {
			typeTempService.delete(ids);
			actionResult = new ActionResult(true,"删除类型模板成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"删除类型模板失败");
		}
		return actionResult;
	}

	@RequestMapping("/add")
	public ActionResult add(TbTypeTemplate typeTemplate){
		logger.info(logStr+"add方法");
		try {
			typeTempService.add(typeTemplate);
			actionResult = new ActionResult(true,"新增类型模板成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"新增类型模板失败");
		}
		return actionResult;
	}

	@RequestMapping("/findOne")
	public TbTypeTemplate findOne(Long id){
		logger.info(logStr+"findOne方法");
		return typeTempService.findOne(id);
	}

	@RequestMapping("/update")
	public ActionResult update(TbTypeTemplate typeTemplate){
		logger.info(logStr+"update方法");
		try {
			typeTempService.update(typeTemplate);
			actionResult = new ActionResult(true,"更新类型模板成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新类型模板失败");
		}
		return actionResult;
	}
}
