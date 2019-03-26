package com.plusmall.search.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.plusmall.model.TbItem;
import com.plusmall.search.ItemSearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@Component
public class ItemSearchListener implements MessageListener {
	private static Logger logger = Logger.getLogger(ItemSearchListener.class);

	@Autowired
	private ItemSearchService itemSearchService;

	@Override
	public void onMessage(Message message) {
		logger.info("监听接收到消息。。。");
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			List<TbItem> list = JSON.parseArray(text, TbItem.class);
			for (TbItem item: list){
				logger.info(item.getId()+" "+item.getTitle());
				Map specMap = JSON.parseObject(item.getSpec());
				item.setSpecMap(specMap);
			}
			itemSearchService.importList(list);
			logger.info("成功导入到索引库");
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
