package com.plusmall.page.impl;

import com.plusmall.mapper.TbGoodsDescMapper;
import com.plusmall.mapper.TbGoodsMapper;
import com.plusmall.mapper.TbItemCatMapper;
import com.plusmall.mapper.TbItemMapper;
import com.plusmall.model.TbGoods;
import com.plusmall.model.TbGoodsDesc;
import com.plusmall.model.TbItem;
import com.plusmall.model.TbItemExample;
import com.plusmall.page.ItemPageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Service
public class ItemPageServiceImpl implements ItemPageService {
	private static Logger logger = Logger.getLogger(ItemPageServiceImpl.class);
	private static String logStr = "进入ItemPageServiceImpl-";

	@Value("${pagedir}")
	private String pagedir;

	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;

	@Autowired
	private TbGoodsMapper goodsMapper;

	@Autowired
	private TbGoodsDescMapper goodsDescMapper;

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Autowired
	private TbItemMapper itemMapper;


	@Override
	public boolean genItemHtml(Long goodsId) {
		logger.info(logStr+"genItemHtml方法");
		try {
			Configuration configuration = freemarkerConfig.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			Map dataModel=new HashMap<>();
			//1.加载商品表数据
			TbGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
			dataModel.put("goods", goods);
			//2.加载商品扩展表数据
			TbGoodsDesc goodsDesc = goodsDescMapper.selectByPrimaryKey(goodsId);
			dataModel.put("goodsDesc", goodsDesc);
			//3.商品分类
			String itemCat1 = itemCatMapper.selectByPrimaryKey(goods.getCategory1Id()).getName();
			String itemCat2 = itemCatMapper.selectByPrimaryKey(goods.getCategory2Id()).getName();
			String itemCat3 = itemCatMapper.selectByPrimaryKey(goods.getCategory3Id()).getName();
			dataModel.put("itemCat1", itemCat1);
			dataModel.put("itemCat2", itemCat2);
			dataModel.put("itemCat3", itemCat3);
			//4.SKU列表
			TbItemExample example=new TbItemExample();
			TbItemExample.Criteria criteria = example.createCriteria();
			criteria.andStatusEqualTo("1");//状态为有效
			criteria.andGoodsIdEqualTo(goodsId);//指定SPU ID
			example.setOrderByClause("is_default desc");//按照状态降序，保证第一个为默认
			List<TbItem> itemList = itemMapper.selectByExample(example);
			dataModel.put("itemList", itemList);

			Writer out=new FileWriter(pagedir+goodsId+".html");
			template.process(dataModel, out);
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteItemHtml(Long[] goodsIds) {
		logger.info(logStr+"deleteItemHtml方法");
		try {
			for (Long goodsId : goodsIds){
				new File(pagedir+goodsId+".html").delete();
			}
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}
}
