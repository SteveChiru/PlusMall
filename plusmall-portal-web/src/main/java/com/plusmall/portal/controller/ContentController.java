package com.plusmall.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.model.TbContent;
import com.plusmall.portal.ContentService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 */
@RestController
@RequestMapping("/content")
public class ContentController {
	private static Logger logger = Logger.getLogger(ContentController.class);
	private static String logStr = "进入ContentController-";

	@Reference
	private ContentService contentService;

	@RequestMapping("/search")
	public List<TbContent> search(Long catId){
		logger.info(logStr+"search方法");
		List<TbContent> contentList = new ArrayList<>();
		try {
			contentList = contentService.search(catId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentList;
	}

}
