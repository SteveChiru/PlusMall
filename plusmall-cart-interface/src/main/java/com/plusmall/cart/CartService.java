package com.plusmall.cart;

import com.plusmall.pojogroup.Cart;

import java.util.List;

/**
 * @Description:
 */
public interface CartService {
	public List<Cart> addGoodsToCartList(List<Cart> cartList,Long itemId,Integer num);
}
