package com.plusmall.search.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.model.TbItem;
import com.plusmall.search.ItemSearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
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
		Query query = new SimpleQuery();
		//添加查询条件
		Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		map.put("rows",page.getContent());

		return map;
	}
}
