package com.plusmall.search.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.model.TbItem;
import com.plusmall.search.ItemSearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;

import java.util.*;

/**
 * @Description:
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {
	private static Logger logger = Logger.getLogger(ItemSearchServiceImpl.class);
	private static String logStr = "进入ItemSearchServiceImpl-";

	@Autowired
	private SolrTemplate solrTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public Map<String, Object> search(Map searchMap) throws NullPointerException {
		logger.info(logStr+"search方法");
		Map<String,Object> map = new HashMap<>();
		HighlightQuery highlightQuery = new SimpleHighlightQuery();
		//1.按关键字查询（高亮显示）
		map.putAll(searchList(searchMap));
		//2.根据关键字查询商品分类
		List<String> catList = searchCategoryList(searchMap);
		map.put("categoryList",catList);
		//3.查询品牌和规格列表
		String categoryName=(String)searchMap.get("category");
		if(!"".equals(categoryName)){//如果有分类名称
			map.putAll(searchBrandAndSpecList(categoryName));
		}else{//如果没有分类名称，按照第一个查询
			if(catList.size() > 0){
				map.putAll(searchBrandAndSpecList(catList.get(0)));
			}
		}
		return map;
	}

	private Map searchList(Map searchMap) {
		Map map=new HashMap();
		HighlightQuery query=new SimpleHighlightQuery();

		//构建高亮选项对象
		HighlightOptions highlightOptions=new HighlightOptions().addField("item_title");//设置高亮的域
		highlightOptions.setSimplePrefix("<em style='color:red'>");//高亮前缀
		highlightOptions.setSimplePostfix("</em>");//高亮后缀
		query.setHighlightOptions(highlightOptions);//设置高亮选项

		//1.1 关键字查询
		Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
		query.addCriteria(criteria);

		//1.2 按商品分类过滤
		if(!"".equals(searchMap.get("category"))  )	{//如果用户选择了分类
			FilterQuery filterQuery=new SimpleFilterQuery();
			Criteria filterCriteria=new Criteria("item_category").is(searchMap.get("category"));
			filterQuery.addCriteria(filterCriteria);
			query.addFilterQuery(filterQuery);
		}

		//1.3 按品牌过滤
		if(!"".equals(searchMap.get("brand"))  )	{//如果用户选择了品牌
			FilterQuery filterQuery=new SimpleFilterQuery();
			Criteria filterCriteria=new Criteria("item_brand").is(searchMap.get("brand"));
			filterQuery.addCriteria(filterCriteria);
			query.addFilterQuery(filterQuery);
		}
		//1.4 按规格过滤
		if(searchMap.get("spec")!=null){
			Map<String,String> specMap= (Map<String, String>) searchMap.get("spec");
			for(String key :specMap.keySet()){

				FilterQuery filterQuery=new SimpleFilterQuery();
				Criteria filterCriteria=new Criteria("item_spec_"+key).is( specMap.get(key)  );
				filterQuery.addCriteria(filterCriteria);
				query.addFilterQuery(filterQuery);

			}

		}
		//1.5按价格筛选
		if(!"".equals(searchMap.get("price"))){
			String[] price = ((String) searchMap.get("price")).split("-");
			if(!price[0].equals("0")){//如果区间起点不等于0
				Criteria filterCriteria=new Criteria("item_price").greaterThanEqual(price[0]);
				FilterQuery filterQuery=new SimpleFilterQuery(filterCriteria);
				query.addFilterQuery(filterQuery);
			}
			if(!price[1].equals("*")){//如果区间终点不等于*
				Criteria filterCriteria=new  Criteria("item_price").lessThanEqual(price[1]);
				FilterQuery filterQuery=new SimpleFilterQuery(filterCriteria);
				query.addFilterQuery(filterQuery);
			}
		}
		//1.6分页查询
		Integer pageNo= (Integer) searchMap.get("pageNo");//提取页码
		if(pageNo==null){
			pageNo=1;//默认第一页
		}
		Integer pageSize=(Integer) searchMap.get("pageSize");//每页记录数
		if(pageSize==null){
			pageSize=20;//默认20
		}
		query.setOffset((pageNo-1)*pageSize);//从第几条记录查询
		query.setRows(pageSize);



		HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query,TbItem.class);
		for (HighlightEntry<TbItem> h : page.getHighlighted()){	//循环高亮入口集合
			TbItem item = h.getEntity();
			if(h.getHighlights().size()>0 && h.getHighlights().get(0).getSnipplets().size()>0){
				item.setTitle(h.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
			}
		}
		map.put("rows",page.getContent());
		map.put("totalPages", page.getTotalPages());//返回总页数
		map.put("total", page.getTotalElements());//返回总记录数
		//判断关键字是否为分类或者品牌
		String keyword = (String) searchMap.get("keywords");
		Set itemCats = redisTemplate.opsForHash().keys("itemCat");
		if (itemCats.contains(keyword)){
			map.put("category",keyword);
		}
		Set allBrand = redisTemplate.opsForSet().members("allBrand");
		if (allBrand.contains(keyword)){
			map.put("brand",keyword);
		}
		return map;
	}

	private Map<String,List> searchBrandAndSpecList(String category) {
		Map<String,List> map = new HashMap<>();
		Long typeId = (Long) redisTemplate.boundHashOps("itemCat").get(category);//获取模板ID
		if(typeId!=null){
			//根据模板ID查询品牌列表
			List brandList = (List) redisTemplate.boundHashOps("brandList").get(typeId);
			map.put("brandList", brandList);//返回值添加品牌列表
			//根据模板ID查询规格列表
			List specList = (List) redisTemplate.boundHashOps("specList").get(typeId);
			map.put("specList", specList);
		}
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
