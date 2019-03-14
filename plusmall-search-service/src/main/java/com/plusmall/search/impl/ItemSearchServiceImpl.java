package com.plusmall.search.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.model.TbItem;
import com.plusmall.search.ItemSearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {
	private static Logger logger = Logger.getLogger(ItemSearchServiceImpl.class);
	private static String logStr = "进入ItemSearchServiceImpl-";

	@Autowired
	private SolrTemplate solrTemplate;

	@Override
	public Map<String, Object> search(Map searchMap) throws NullPointerException {
		logger.info(logStr+"search方法");
		Map<String,Object> map = new HashMap<>();
		//1.按关键字查询高亮显示
		HighlightQuery highlightQuery = new SimpleHighlightQuery();
		HighlightOptions highlightOptions = new HighlightOptions();
		highlightOptions.addField("item_title");	//设置高亮的域
		highlightOptions.setSimplePrefix("<em style='color:red'>");//高亮前缀
		highlightOptions.setSimplePostfix("</em>");//高亮后缀
		highlightQuery.setHighlightOptions(highlightOptions);	//设置高亮选项

		//添加查询条件
		Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
		highlightQuery.addCriteria(criteria);
		HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(highlightQuery, TbItem.class);
		for (HighlightEntry<TbItem> h : page.getHighlighted()){	//循环高亮入口集合
			TbItem item = h.getEntity();
			if(h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0){
				item.setTitle(h.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
			}
		}
		map.put("rows",page.getContent());
		//2.根据关键字查询商品分类
		List<String> catList = searchCategoryList(searchMap);
		map.put("categoryList",catList);
		return map;
	}

	private List<String> searchCategoryList(Map searchMap) {
		logger.info(logStr+"searchCategoryList方法");
		List<String> list=new ArrayList();
		Query query=new SimpleQuery();
		//按照关键字查询
		Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);
		//设置分组选项
		GroupOptions groupOptions=new GroupOptions().addGroupByField("item_category");
		query.setGroupOptions(groupOptions);
		//得到分组页
		GroupPage<TbItem> page = solrTemplate.queryForGroupPage(query, TbItem.class);
		//根据列得到分组结果集
		GroupResult<TbItem> groupResult = page.getGroupResult("item_category");
		//得到分组结果入口页
		Page<GroupEntry<TbItem>> groupEntries = groupResult.getGroupEntries();
		//得到分组入口集合
		List<GroupEntry<TbItem>> content = groupEntries.getContent();
		for(GroupEntry<TbItem> entry:content){
			list.add(entry.getGroupValue());//将分组结果的名称封装到返回值中
		}
		return list;

	}
}
