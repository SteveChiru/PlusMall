package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbGoods;
import com.plusmall.operator.GoodsAuditService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/goodsAudit")
public class GoodsAuditController {
	private static Logger logger = Logger.getLogger(GoodsAuditController.class);
	private static String logStr = "进入GoodsAuditController-";
	private static PageResult pageResult;
	private static ActionResult actionResult;

	@Reference
	private GoodsAuditService goodsAuditService;

	@RequestMapping("/search")
	public PageResult search(int pageNum, int pageSize, @RequestBody TbGoods tbGoods){
		logger.info(logStr+"进入search方法");
		return goodsAuditService.search(pageNum,pageSize,tbGoods);
	}

	@RequestMapping("/updateStatus")
	public ActionResult updateStatus(Long[] ids,String status){
		logger.info(logStr+"updateStatus");
		try {
			goodsAuditService.updateStatus(ids,status);
			actionResult = new ActionResult(true,"成功更新商品状态");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"失败更新商品状态");
		}
		return actionResult;
	}

	@RequestMapping("/delete")
	public ActionResult delete(Long[] ids){
		logger.info(logStr+"delete方法");
		try {
			goodsAuditService.delete(ids);
			actionResult = new ActionResult(true,"成功删除商品信息");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"失败删除商品信息");
		}
		return actionResult;
	}
}
