package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.PageResult;
import com.plusmall.operator.Brand;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
	private static Logger logger = Logger.getLogger(BrandController.class);

	@Reference
	private Brand brand;

	@RequestMapping("/getAll")
	@ResponseBody
	public PageResult getAllBrands(){
		logger.info("进入controller-getAllBrands方法");
		PageResult result = brand.getBrands();
		return result;
	}
}
