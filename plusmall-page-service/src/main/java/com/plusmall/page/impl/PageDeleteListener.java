package com.plusmall.page.impl;

import com.plusmall.page.ItemPageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;

/**
 * @Description:
 */
@Component
public class PageDeleteListener implements MessageListener {

	private static Logger logger = Logger.getLogger(PageDeleteListener.class);

	@Autowired
	private ItemPageService itemPageService;

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			Long[] goodsIds = (Long[]) objectMessage.getObject();
			logger.info("ItemDeleteListener监听接收到消息。。。"+goodsIds);
			boolean b = itemPageService.deleteItemHtml(goodsIds);
			logger.info("网页删除结果："+b);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
