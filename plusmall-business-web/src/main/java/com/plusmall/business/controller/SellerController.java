package com.plusmall.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.business.SellerService;
import com.plusmall.commons.ActionResult;
import com.plusmall.model.TbSeller;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
	private static Logger logger = Logger.getLogger(SellerController.class);
	private static String logStr = "进入SellerController-";
	private static ActionResult actionResult;

	@Reference
	private SellerService sellerService;

	@RequestMapping("/add")
	public ActionResult addSeller(@RequestBody TbSeller seller){
		logger.info(logStr+"add方法");
		try {
			sellerService.add(seller);
			actionResult = new ActionResult(true,"新增商家成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"新增商家失败");
		}
		return actionResult;
	}
}
