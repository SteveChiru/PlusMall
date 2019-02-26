package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
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

	@Reference
	private GoodsAuditService goodsAuditService;

	@RequestMapping("/search")
	public PageResult search(int pageNum, int pageSize, @RequestBody TbGoods tbGoods){
		logger.info(logStr+"进入search方法");
		return goodsAuditService.search(pageNum,pageSize,tbGoods);
	}
}
