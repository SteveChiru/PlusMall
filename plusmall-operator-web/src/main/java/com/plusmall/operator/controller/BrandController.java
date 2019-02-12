package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbBrand;
import com.plusmall.operator.BrandService;
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
	private static ActionResult actionResult;

	@Reference
	private BrandService brandService;

	@RequestMapping("/addBrand")
	public ActionResult addBrand(@RequestBody TbBrand brandinfo){
		logger.info("进入BrandController-addBrand方法");
		try {
			logger.info("firstChar:"+brandinfo.getFirstChar());
			brandService.add(brandinfo);
			actionResult = new ActionResult(true,"添加成功");
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"添加失败");
		}finally {
			return actionResult;
		}
	}

	@RequestMapping("/findOne")
	public TbBrand findOne(Long id){
		logger.info("进入BrandController-findOne方法");
		TbBrand brandById = brandService.getBrandById(id);
		return brandById;
	}

	@RequestMapping("/updateBrand")
	public ActionResult updateBrand(@RequestBody TbBrand brandinfo){
		logger.info("进入BrandController-updateBrand方法");
		try {
			brandService.update(brandinfo);
			actionResult = new ActionResult(true,"更新成功");
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"更新失败");
		}finally {
			return actionResult;
		}
	}

	@RequestMapping("/delete")
	public ActionResult deleteBrand(Long[] ids){
		logger.info("进入BrandController-delete方法");
		try {
			System.out.println(ids);
			brandService.delete(ids);
			actionResult = new ActionResult(true,"删除品牌成功");
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"删除品牌失败");
		}finally {
			return actionResult;
		}
	}

	@RequestMapping("/search")
	public PageResult searchBrands(@RequestBody TbBrand brandinfo,int pageNum,int pageSize){
		logger.info("进入BrandController-searchBrands方法");
		PageResult result = brandService.searchBrands(brandinfo, pageNum, pageSize);
		return result;
	}
}
