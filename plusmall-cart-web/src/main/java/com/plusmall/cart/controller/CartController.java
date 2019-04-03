package com.plusmall.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.plusmall.cart.CartService;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.CookieUtil;
import com.plusmall.pojogroup.Cart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 */
@RestController
@RequestMapping("/cart")
public class CartController {

	private static Logger logger = Logger.getLogger(CartController.class);

	@Reference(timeout = 6000)
	private CartService cartService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	/**
	 * 购物车列表
	 * @return
	 */
	@RequestMapping("/findCartList")
	public List<Cart> findCartList(){
		//当前登录人账号
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		String cartListString = CookieUtil.getCookieValue(request,"cartList","UTF-8");
		if (cartListString==null || cartListString.equals("")){
			cartListString = "[]";
		}
		List<Cart> cartList_cookie = JSON.parseArray(cartListString,Cart.class);
		if (name.equals("anonymousUser")){	//如果未登录
			return cartList_cookie;
		}else {	//如果已登录
			List<Cart> cartList_redis =cartService.findCartListFromRedis(name);//从redis中提取
			if (cartList_cookie.size() > 0){		//如果本地存在购物车
				//合并购物车
				cartList_redis = cartService.mergeCartList(cartList_redis,cartList_cookie);
				//清除本地cookie的数据
				CookieUtil.deleteCookie(request,response,"cartList");
				//将合并后的数据存入redis
				cartService.saveCartListToRedis(name,cartList_redis);
			}
			return cartList_redis;
		}
	}

	/**
	 * 添加商品到购物车
	 * @param itemId
	 * @param num
	 * @return
	 */
	@RequestMapping("/addGoodsToCartList")
	@CrossOrigin(origins = "http://localhost:9490",allowCredentials = "true")
	public ActionResult addGoodsToCartList(Long itemId,Integer num){

		//当前登录人账号
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		try {
			List<Cart> cartList = findCartList();	//获取购物车列表
			cartList = cartService.addGoodsToCartList(cartList, itemId, num);
			if (name.equals("anonymousUser")){	//如果是登录，保存到cookie
				CookieUtil.setCookie(request,response,"cartList",JSON.toJSONString(cartList),3600*24,"UTF-8");
				logger.info("向cookie存入数据");
			}else {
				cartService.saveCartListToRedis(name,cartList);
			}
			return new ActionResult(true,"成功把商品添加到购物车");
		}catch (Exception e){
			e.printStackTrace();
			return new ActionResult(false,"添加失败");
		}

	}
}
