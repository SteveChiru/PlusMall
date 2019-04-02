package com.plusmall.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.plusmall.cart.CartService;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.CookieUtil;
import com.plusmall.pojogroup.Cart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Reference
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
		String cartListString = CookieUtil.getCookieValue(request,"cartList","UTF-8");
		if (cartListString==null || cartListString.equals("")){
			cartListString = "[]";
		}
		List<Cart> cartList_cookie = JSON.parseArray(cartListString,Cart.class);
		return cartList_cookie;
	}

	/**
	 * 添加商品到购物车
	 * @param itemId
	 * @param num
	 * @return
	 */
	@RequestMapping("/addGoodsToCartList")
	public ActionResult addGoodsToCartList(Long itemId,Integer num){
		try {
			List<Cart> cartList = findCartList();	//获取购物车列表
			cartList = cartService.addGoodsToCartList(cartList, itemId, num);
			CookieUtil.setCookie(request,response,"cartList",JSON.toJSONString(cartList),3600*24,"UTF-8");
			return new ActionResult(true,"成功把商品添加到购物车");
		}catch (Exception e){
			e.printStackTrace();
			return new ActionResult(false,"添加失败");
		}

	}
}
