package com.plusmall.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.business.SellerService;
import com.plusmall.commons.ActionResult;
import com.plusmall.model.TbSeller;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private static ActionResult actionResult;

	@Reference
	private SellerService sellerService;

	@RequestMapping("/add")
	public ActionResult addSeller(@RequestBody TbSeller seller){
		logger.info(logStr+"add方法");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			String password = passwordEncoder.encode(seller.getPassword());
			seller.setPassword(password);
			sellerService.add(seller);
			actionResult = new ActionResult(true,"新增商家成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"新增商家失败");
		}
		return actionResult;
	}

	@RequestMapping("/update")
	public ActionResult updateSeller(@RequestBody TbSeller seller){
		logger.info(logStr+"update方法");
		try {
			sellerService.update(seller);
			actionResult = new ActionResult(true,"更新用户信息成功");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新用户信息失败");
		}
		return actionResult;
	}

	@RequestMapping("/findOne")
	public TbSeller findOne(String sellerId){
		logger.info(logStr+"findOne方法");
		try {
			sellerId = new String(sellerId.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sellerService.findOne(sellerId);
	}

	@RequestMapping("/checkOrgPwd")
	public ActionResult checkOrgPwd(String orgPwd){
		logger.info(logStr+"checkOrgPwd方法");
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String pwdHashed = sellerService.findOne(name).getPassword();
		if (BCrypt.checkpw(orgPwd,pwdHashed)){
			actionResult = new ActionResult(true,"密码验证通过");
		}else {
			actionResult = new ActionResult(false,"密码验证未通过");
		}
		return actionResult;
	}

	@RequestMapping("/updateNewPwd")
	public ActionResult updateNewPwd(String newPwd){
		logger.info(logStr+"updateNewPwd方法");
		//根据当前登录商家名字，找到该商家的全部信息
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		TbSeller seller = sellerService.findOne(name);
		//对新设置的密码进行加密处理
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(newPwd);
		//设置新密码
		seller.setPassword(password);
		//更新用户信息
		try {
			sellerService.update(seller);
			actionResult = new ActionResult(true,"更新用户密码成功");
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新用户密码出错");
		}
		return actionResult;
	}
}
