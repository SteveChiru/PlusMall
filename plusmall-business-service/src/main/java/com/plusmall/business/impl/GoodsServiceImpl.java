package com.plusmall.business.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.business.GoodsService;
import com.plusmall.mapper.TbGoodsDescMapper;
import com.plusmall.mapper.TbGoodsMapper;
import com.plusmall.pojogroup.Goods;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Override
	public void add(Goods goods) throws NullPointerException {
		logger.info(logStr+"add方法");
		goods.getTbGoods().setAuditStatus("0");		//设置未申请状态
		goodsMapper.insert(goods.getTbGoods());
		goods.getTbGoodsDesc().setGoodsId(goods.getTbGoods().getId());	//设置ID
		goodsDescMapper.insert(goods.getTbGoodsDesc());	//插入商品扩展数据
	}
}
