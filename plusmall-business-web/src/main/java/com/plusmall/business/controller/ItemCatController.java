package com.plusmall.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.business.ItemCatService;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbItemCat;
import org.apache.log4j.Logger;
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

	@Reference
	private ItemCatService itemCatService;

	@RequestMapping("/searchByParentId")
	public PageResult searchByParentId(Long parentId){
		logger.info(logStr+"searchByParentId方法");
		return itemCatService.searchByParentId(parentId);
	}

	@RequestMapping("/findOne")
	public TbItemCat findOne(Long id){
		logger.info(logStr+"findOne方法");
		return itemCatService.findOne(id);
	}
}
