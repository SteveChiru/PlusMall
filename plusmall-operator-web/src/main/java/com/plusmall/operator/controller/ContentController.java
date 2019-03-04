package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbContent;
import com.plusmall.operator.ContentService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/content")
public class ContentController {
	private static Logger logger = Logger.getLogger(ContentController.class);
	private static String logStr = "ContentController-";
	private static PageResult pageResult;
	private static ActionResult actionResult;

	@Reference
	private ContentService contentService;

	@RequestMapping("/search")
	public PageResult search(int pageNum,int pageSize){
		logger.info(logStr+"search方法");
		try {
			pageResult = contentService.search(pageNum,pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageResult;
	}

	@RequestMapping("/delete")
	public ActionResult delete(Long[] ids){
		logger.info(logStr+"delete方法");
		try {
			contentService.delete(ids);
			actionResult = new ActionResult(true,"删除广告成功");
		} catch (NullPointerException e) {
			e.printStackTrace();
			actionResult = new ActionResult(false,"删除广告失败");
		}
		return actionResult;
	}

	@RequestMapping("/add")
	public ActionResult add(@RequestBody TbContent content){
		logger.info(logStr+"add方法");
		try {
			contentService.add(content);
			actionResult = new ActionResult(true,"添加广告成功");
		} catch (NullPointerException e) {
			e.printStackTrace();
			actionResult = new ActionResult(false,"添加广告失败");
		}
		return actionResult;
	}

	@RequestMapping("/findOne")
	public TbContent findOne(Long id){
		logger.info(logStr+"findOne方法");
		return contentService.findOne(id);
	}

	@RequestMapping("/update")
	public ActionResult update(@RequestBody TbContent content){
		logger.info(logStr+"update方法");
		try {
			contentService.update(content);
			actionResult = new ActionResult(true,"更新广告成功");
		} catch (NullPointerException e) {
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新广告失败");
		}
		return actionResult;
	}
}
