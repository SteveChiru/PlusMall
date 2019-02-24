package com.plusmall.business.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.plusmall.business.GoodsService;
import com.plusmall.mapper.*;
import com.plusmall.model.TbBrand;
import com.plusmall.model.TbItem;
import com.plusmall.model.TbItemCat;
import com.plusmall.model.TbSeller;
import com.plusmall.pojogroup.Goods;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	private static Logger logger = Logger.getLogger(GoodsServiceImpl.class);
	private static String logStr = "进入GoodsServiceImpl-";

	@Autowired
	private TbGoodsMapper goodsMapper;
	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbBrandMapper brandMapper;
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private TbSellerMapper sellerMapper;

	@Override
	public void add(Goods goods) throws NullPointerException {
		logger.info(logStr+"add方法");
		goods.getTbGoods().setAuditStatus("0");		//设置未申请状态
		goodsMapper.insert(goods.getTbGoods());
		goods.getTbGoodsDesc().setGoodsId(goods.getTbGoods().getId());	//设置ID
		goodsDescMapper.insert(goods.getTbGoodsDesc());	//插入商品扩展数据
		if ("1".equals(goods.getTbGoods().getIsEnableSpec())){
			for (TbItem item:goods.getItemList()){
				//标题
				String title = goods.getTbGoods().getGoodsName();
				Map<String,Object> specMap = JSON.parseObject(item.getSpec());
				for (String key:specMap.keySet()){
					title+=""+specMap.get(key);
				}
				item.setTitle(title);
				setItemValues(goods,item);
				itemMapper.insert(item);
			}
		}else {
			TbItem item = new TbItem();
			item.setTitle(goods.getTbGoods().getGoodsName());
			item.setPrice(goods.getTbGoods().getPrice());	//价格
			item.setStatus("1");	//状态
			item.setIsDefault("1");	//是否默认
			item.setNum(99999);	//库存数量
			item.setSpec("{}");
			setItemValues(goods,item);
			itemMapper.insert(item);
		}

	}

	private void setItemValues(Goods goods,TbItem item){
		item.setGoodsId(goods.getTbGoods().getId());	//商品SPU编号
		item.setSellerId(goods.getTbGoods().getSellerId());	//商家编号
		item.setCategoryid(goods.getTbGoods().getCategory3Id());	//商品分类编号（3级）
		item.setCreateTime(new Date());	//创建日期
		item.setUpdateTime(new Date());	//修改日期
		//品牌名称
		TbBrand brand = brandMapper.selectByPrimaryKey(goods.getTbGoods().getBrandId());
		item.setBrand(brand.getName());
		//分类名称
		TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(goods.getTbGoods().getCategory3Id());
		item.setCategory(itemCat.getName());
		//商家名称
		TbSeller seller = sellerMapper.selectByPrimaryKey(goods.getTbGoods().getSellerId());
		item.setSeller(seller.getNickName());
		//图片地址(取SPU的第一个图片)
		List<Map> imageList = JSON.parseArray(goods.getTbGoodsDesc().getItemImages(), Map.class);
		if (imageList.size()>0){
			item.setImage((String) imageList.get(0).get("url"));
		}
	}
}
