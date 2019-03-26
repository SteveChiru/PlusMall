package com.plusmall.search.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.search.ItemSearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @Description:
 */
@Component
public class ItemDeleteListener implements MessageListener {

	private static Logger logger = Logger.getLogger(ItemDeleteListener.class);

	@Autowired
	private ItemSearchService itemSearchService;

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			Long[] goodsIds = (Long[]) objectMessage.getObject();
			logger.info("ItemDeleteListener监听接收到消息。。。"+goodsIds);
			itemSearchService.deleteByGoodsIds(Arrays.asList(goodsIds));
			logger.info("成功删除索引库中的记录");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
