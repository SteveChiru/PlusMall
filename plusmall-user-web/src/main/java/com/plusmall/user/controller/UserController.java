package com.plusmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PhoneFormatCheckUtils;
import com.plusmall.model.TbUser;
import com.plusmall.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	private static String logStr = "进入UserController-";
	private static ActionResult actionResult;

	@Reference
	private UserService userService;

	@RequestMapping("/add")
	public ActionResult addUser(@RequestBody TbUser user,String smscode){
		logger.info(logStr+"addUser方法");
		boolean checkSmsCode = userService.checkSmsCode(user.getPhone(),smscode);
		if (checkSmsCode == false){
			return new ActionResult(false,"验证码输入错误！");
		}

		try {
			userService.add(user);
			actionResult = new ActionResult(true,"添加用户成功");
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"添加用户失败");
		}
		return actionResult;
	}

	@RequestMapping("/sendCode")
	public ActionResult sendCode(String phone){
		logger.info(logStr+"sendCode方法");
		if (!PhoneFormatCheckUtils.isPhoneLegal(phone)){
			return new ActionResult(false,"手机号不正确");
		}

		try {
			userService.createSmsCode(phone);
			return new ActionResult(true,"验证码发送成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ActionResult(false,"验证码发送失败");
		}
	}
}
