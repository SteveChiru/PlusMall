package com.plusmall.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.business.GoodsService;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbGoods;
import com.plusmall.model.TbItemCat;
import com.plusmall.pojogroup.Goods;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
	private static Logger logger = Logger.getLogger(GoodsController.class);
	private static String logStr = "进入GoodsController-";
	private static ActionResult actionResult;
	private static PageResult pageResult;

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

	@RequestMapping("/search")
	public PageResult search(int pageNum, int pageSize, @RequestBody TbGoods tbGoods){
		logger.info(logStr+"search方法");
		//获取商家ID
		String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
		//添加查询条件
		tbGoods.setSellerId(sellerId);
		return goodsService.search(pageNum,pageSize,tbGoods);
	}

	@RequestMapping("/delete")
	public ActionResult delete(Long[] ids){
		logger.info(logStr+"delete方法");
		try {
			goodsService.delete(ids);
			actionResult = new ActionResult(true,"删除多个商品成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"删除多个商品失败");
		}
		return actionResult;
	}

	@RequestMapping("/findOne")
	public Goods findOne(Long id){
		logger.info(logStr+"findOne方法");
		return goodsService.findOne(id);
	}
}
