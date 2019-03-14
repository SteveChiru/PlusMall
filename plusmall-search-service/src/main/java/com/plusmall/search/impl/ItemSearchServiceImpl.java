package com.plusmall.search.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.model.TbItem;
import com.plusmall.search.ItemSearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.HashMap;
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
		return map;
	}
}
