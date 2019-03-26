package com.plusmall.page.impl;

import com.plusmall.page.ItemPageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Description:
 */
@Component
public class PageListener implements MessageListener {

	private static Logger logger = Logger.getLogger(PageListener.class);

	@Autowired
	private ItemPageService itemPageService;

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			logger.info("接收到消息："+text);
			boolean b = itemPageService.genItemHtml(Long.parseLong(text));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
