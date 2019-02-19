package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbItemCat;
import com.plusmall.operator.ItemCatService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/itemcat")
public class ItemCatController {
	private static Logger logger = Logger.getLogger(ItemCatController.class);
	private static String logStr = "进入ItemCatController-";
	private static PageResult pageResult;
	private static ActionResult actionResult;

	@Reference
	private ItemCatService itemCatService;

	@RequestMapping("/search")
	public PageResult search(int pageNum,int pageSize,Long parentId){
		logger.info(logStr+"search方法");
		return itemCatService.searchByParentId(pageNum, pageSize, parentId);
	}

	@RequestMapping("/delete")
	public ActionResult delete(Long[] ids){
		logger.info(logStr+"/delete方法");
		try {
			itemCatService.delete(ids);
			actionResult = new ActionResult(true,"成功删除所选商品类别");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"您没有选择商品类别，请确认后重新删除");
		}
		return actionResult;
	}

	@RequestMapping("/add")
	public ActionResult add(@RequestBody TbItemCat itemCat){
		logger.info(logStr+"add方法");
		try {
			itemCatService.add(itemCat);
			actionResult = new ActionResult(true,"添加商品类别成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"添加商品类别失败");
		}
		return actionResult;
	}

	@RequestMapping("/findOne")
	public TbItemCat findOne(Long id){
		logger.info(logStr+"findOne方法");
		return itemCatService.findOne(id);
	}

	@RequestMapping("/update")
	public ActionResult update(@RequestBody TbItemCat itemCat){
		logger.info(logStr+"update方法");
		try {
			itemCatService.update(itemCat);
			actionResult = new ActionResult(true,"更新商品类型成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新商品类型失败");
		}
		return actionResult;
	}
}
