package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbBrand;
import com.plusmall.operator.Brand;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
	private static Logger logger = Logger.getLogger(BrandController.class);

	@Reference
	private Brand brand;

	@RequestMapping("/getPage")
	public PageResult getBrandsPerPage(int pageNum,int pageSize){
		logger.info("进入BrandController-getBrandsPerPage方法");
		PageResult result = brand.getBrandsPerPae(pageNum, pageSize);
		return result;
	}

	@RequestMapping("/addBrand")
	public ActionResult addBrand(@RequestBody TbBrand branddata){
		logger.info("进入BrandController-addBrand方法");
		try {
			logger.info("firstChar:"+branddata.getFirstChar());
			brand.add(branddata);
			return new ActionResult(true,"添加成功");
		}catch (Exception e){
			e.printStackTrace();
			return new ActionResult(false,"添加失败");
		}

	}
}
