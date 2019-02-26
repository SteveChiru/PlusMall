package com.plusmall.business.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.business.GoodsService;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.*;
import com.plusmall.model.*;
import com.plusmall.pojogroup.Goods;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Service
@Transactional
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

	@Override
	public PageResult search(int pageNum, int pageSize, TbGoods tbGoods) {
		logger.info(logStr+"search方法");
		PageHelper.startPage(pageNum,pageSize);
		TbGoodsExample example = new TbGoodsExample();
		TbGoodsExample.Criteria criteria = example.createCriteria();
		if (tbGoods.getSellerId()!=null && tbGoods.getSellerId().length() > 0){
			criteria.andSellerIdEqualTo(tbGoods.getSellerId());
		}
		if (tbGoods.getAuditStatus() != null && tbGoods.getAuditStatus().length() > 0){
			criteria.andAuditStatusEqualTo(tbGoods.getAuditStatus());
		}
		if (tbGoods.getGoodsName() != null && tbGoods.getGoodsName().length() > 0){
			criteria.andGoodsNameLike("%"+tbGoods.getGoodsName()+"%");
		}
		Page<TbGoods> page = (Page<TbGoods>) goodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {
		logger.info(logStr+"deleteTbGoods方法");
		for (Long id: ids){
			//删除商品表中的商品信息
			goodsMapper.deleteByPrimaryKey(id);
			//删除商品描述表中的商品信息
			goodsDescMapper.deleteByPrimaryKey(id);
			//删除库存表内的商品
			TbItemExample example = new TbItemExample();
			TbItemExample.Criteria criteria = example.createCriteria();
			criteria.andGoodsIdEqualTo(id);
			itemMapper.deleteByExample(example);
		}
	}

	@Override
	public Goods findOne(Long id) {
		logger.info(logStr+"findOne方法");
		//查找商品数据
		TbGoods tbGoods = goodsMapper.selectByPrimaryKey(id);
		//查找商品描述数据
		TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
		//查找商品SKU数据
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(id);
		List<TbItem> tbItems = itemMapper.selectByExample(example);
		return new Goods(tbGoods,tbGoodsDesc,tbItems);
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
