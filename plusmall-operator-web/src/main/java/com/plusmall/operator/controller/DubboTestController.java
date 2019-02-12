package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.operator.DubboTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 */
@Controller
@RequestMapping("/test")
public class DubboTestController {
	@Reference
	private DubboTest dubboTest;

	@RequestMapping("/showName")
	@ResponseBody
	public String showName(){
		return dubboTest.getName();
	}

}
