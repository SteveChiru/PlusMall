package com.plusmall.solrutil;

import com.alibaba.fastjson.JSON;
import com.plusmall.mapper.TbItemMapper;
import com.plusmall.model.TbItem;
import com.plusmall.model.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Component
public class SolrUtil {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private SolrTemplate solrTemplate;

	//导入商品数据
	public void importItemData(){
		TbItemExample example=new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo("1");//已审核
		List<TbItem> itemList = itemMapper.selectByExample(example);
		System.out.println("===商品列表===");
		for(TbItem item:itemList){
			Map specMap = JSON.parseObject(item.getSpec());
			item.setSpecMap(specMap);
			System.out.println(item.getTitle());
		}
		solrTemplate.saveBeans(itemList);
		solrTemplate.commit();
		System.out.println("===结束===");
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
		SolrUtil solrUtil = (SolrUtil) context.getBean("solrUtil");	//bean的name一般都首字母小写
		solrUtil.importItemData();
	}

}
