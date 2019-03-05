package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbContentCategory;
import com.plusmall.operator.ContentCatService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 */
@RestController
@RequestMapping("/contentCat")
public class ContentCatController {
	private static Logger logger = Logger.getLogger(ContentCatController.class);
	private static String logStr = "进入ContentCatController-";
	private static PageResult pageResult;
	private static ActionResult actionResult;

	@Reference
	private ContentCatService contentCatService;

	@RequestMapping("/search")
	public PageResult search(int pageNum,int pageSize){
		logger.info(logStr+"search方法");
		try {
			pageResult = contentCatService.search(pageNum, pageSize);
			actionResult = new ActionResult(true,"查找广告分类成功");
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"查找广告分类失败");
		}
		return pageResult;
	}

	@RequestMapping("/delete")
	public ActionResult delete(Long[] ids){
		logger.info(logStr+"delete方法");
		try {
			contentCatService.delete(ids);
			actionResult = new ActionResult(true,"删除广告分类成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"删除广告分类失败");
		}
		return actionResult;
	}

	@RequestMapping("/add")
	public ActionResult add(@RequestBody TbContentCategory contentCategory){
		logger.info(logStr+"add方法");
		try {
			contentCatService.add(contentCategory);
			actionResult = new ActionResult(true,"添加广告分类成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"添加广告分类失败");
		}
		return actionResult;
	}

	@RequestMapping("/findOne")
	public TbContentCategory findOne(Long id){
		logger.info(logStr+"findOne方法");
		return contentCatService.findOne(id);
	}

	@RequestMapping("/update")
	public ActionResult update(@RequestBody TbContentCategory contentCategory){
		logger.info(logStr+"update方法");
		try {
			contentCatService.update(contentCategory);
			actionResult = new ActionResult(true,"更新广告分类成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新广告分类失败");
		}
		return actionResult;
	}

	@RequestMapping("/findAll")
	public List<TbContentCategory> findAll(){
		logger.info(logStr+"findAll方法");
		return contentCatService.findAll();
	}
}
