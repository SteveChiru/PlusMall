package com.plusmall.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.search.ItemSearchService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description:
 */
@RestController
@RequestMapping("/itemsearch")
public class ItemSearchController {
	private static Logger logger = Logger.getLogger(ItemSearchController.class);
	private static String logStr = "进入ItemSearchController-";
	private static Map<String,Object> searchResult;

	@Reference
	private ItemSearchService itemSearchService;

	@RequestMapping("/search")
	public Map<String,Object> search(@RequestBody Map searchMap){
		logger.info(logStr+"search方法");
		try {
			searchResult = itemSearchService.search(searchMap);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return searchResult;
	}
}
