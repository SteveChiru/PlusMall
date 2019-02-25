package com.plusmall.pojogroup;

import com.plusmall.model.TbGoods;
import com.plusmall.model.TbGoodsDesc;
import com.plusmall.model.TbItem;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 */
public class Goods implements Serializable {
	private TbGoods tbGoods;		//商品SPU
	private TbGoodsDesc tbGoodsDesc;		//商品扩展
	private List<TbItem> itemList;	//商品SKU列表

	public Goods(TbGoods tbGoods, TbGoodsDesc tbGoodsDesc, List<TbItem> itemList) {
		this.tbGoods = tbGoods;
		this.tbGoodsDesc = tbGoodsDesc;
		this.itemList = itemList;
	}

	public TbGoods getTbGoods() {
		return tbGoods;
	}

	public void setTbGoods(TbGoods tbGoods) {
		this.tbGoods = tbGoods;
	}

	public TbGoodsDesc getTbGoodsDesc() {
		return tbGoodsDesc;
	}

	public void setTbGoodsDesc(TbGoodsDesc tbGoodsDesc) {
		this.tbGoodsDesc = tbGoodsDesc;
	}

	public List<TbItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<TbItem> itemList) {
		this.itemList = itemList;
	}
}
