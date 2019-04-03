package com.plusmall.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbAddress;
import com.plusmall.user.AddressService;
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
@RequestMapping("/address")
public class AddressController {

	private static Logger logger = Logger.getLogger(AddressController.class);
	private static String logStr = "进入AddressController-";
	private static ActionResult actionResult;

	@Reference
	private AddressService addressService;

	@RequestMapping("/findAll")
	public List<TbAddress> findAll(){
		return addressService.findAll();
	}

	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return addressService.findPage(page,rows);
	}

	@RequestMapping("/add")
	public ActionResult add(@RequestBody TbAddress address){
		try {
			addressService.add(address);
			actionResult = new ActionResult(true,"修改成功");
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"修改失败");
		}
		return actionResult;
	}

	@RequestMapping("/findOne")
	public TbAddress findOne(Long id){
		return addressService.findOne(id);
	}

	@RequestMapping("/delete")
	public ActionResult delete(Long[] ids){
		try {
			addressService.delete(ids);
			actionResult = new ActionResult(true,"删除成功");
		}catch (Exception e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"删除失败");
		}
		return actionResult;
	}

	@RequestMapping("/search")
	public PageResult search(@RequestBody TbAddress address, int page, int rows){
		return addressService.findPage(address,page,rows);
	}

	@RequestMapping("/findListByLoginUser")
	public List<TbAddress> findListByLoginUser(){
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		return addressService.findListByUserId(userId);
	}
}
