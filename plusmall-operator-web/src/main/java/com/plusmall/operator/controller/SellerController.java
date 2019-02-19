package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbSeller;
import com.plusmall.operator.SellerService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @Description:
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
	private static Logger logger = Logger.getLogger(SellerController.class);
	private static String logStr = "进入SellerController-";
	private static PageResult pageResult;
	private static ActionResult actionResult;

	@Reference
	private SellerService sellerService;

	@RequestMapping("/search")
	public PageResult search(int pageNum, int pageSize, @RequestBody TbSeller seller){
		logger.info(logStr+"search方法");
		return sellerService.search(pageNum,pageSize,seller);
	}

	@RequestMapping("/findOne")
	public TbSeller findOne(String strId){
		logger.info(logStr+"findOne方法");
		try {
			strId = new String(strId.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sellerService.findOne(strId);
	}

	@RequestMapping("/update")
	public ActionResult update(@RequestBody TbSeller seller){
		logger.info(logStr+"update方法");
		try {
			sellerService.udpate(seller);
			actionResult = new ActionResult(true,"更新商家信息成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新商家信息失败");
		}
		return actionResult;
	}

	@RequestMapping("/updateStatus")
	public ActionResult updateStatus(String strId,String status){
		logger.info(logStr+"updateStatus方法");
		try {
			strId = new String(strId.getBytes("ISO-8859-1"),"utf-8");
			status = new String(status.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sellerService.updateStatus(strId,status);
		return new ActionResult(true,"操作完成");
	}
}
