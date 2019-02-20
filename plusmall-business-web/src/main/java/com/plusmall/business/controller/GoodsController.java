package com.plusmall.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.business.GoodsService;
import com.plusmall.commons.ActionResult;
import com.plusmall.pojogroup.Goods;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
	private static Logger logger = Logger.getLogger(GoodsController.class);
	private static String logStr = "进入GoodsController-";
	private static ActionResult actionResult;

	@Reference
	private GoodsService goodsService;

	@RequestMapping("/add")
	public ActionResult add(@RequestBody Goods goods){
		logger.info(logStr+"add方法");
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		goods.getTbGoods().setSellerId(sellerId);	//设置商家ID
		try {
			goodsService.add(goods);
			actionResult = new ActionResult(true,"增加商品成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"增加商品失败");
		}
		return actionResult;
	}
}
