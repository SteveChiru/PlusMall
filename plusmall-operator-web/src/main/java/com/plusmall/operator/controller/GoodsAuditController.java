package com.plusmall.operator.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.plusmall.commons.ActionResult;
import com.plusmall.commons.PageResult;
import com.plusmall.model.TbGoods;
import com.plusmall.model.TbItem;
import com.plusmall.operator.GoodsAuditService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

/**
 * @Description:
 */
@RestController
@RequestMapping("/goodsAudit")
public class GoodsAuditController {
	private static Logger logger = Logger.getLogger(GoodsAuditController.class);
	private static String logStr = "进入GoodsAuditController-";
	private static PageResult pageResult;
	private static ActionResult actionResult;

	@Reference
	private GoodsAuditService goodsAuditService;

	@Autowired
	private Destination queueSolrDestination;	//用于发送solr导入的消息
	@Autowired
	private Destination queueSolrDeleteDestination;	//用户在索引库中删除记录
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Destination topicPageDestination;
	@Autowired
	private Destination topicPageDeleteDestination;

	@RequestMapping("/search")
	public PageResult search(int pageNum, int pageSize, @RequestBody TbGoods tbGoods){
		logger.info(logStr+"进入search方法");
		return goodsAuditService.search(pageNum,pageSize,tbGoods);
	}

	@RequestMapping("/updateStatus")
	public ActionResult updateStatus(Long[] ids,String status){
		logger.info(logStr+"updateStatus");
		try {
			goodsAuditService.updateStatus(ids,status);
			//按照SPU ID查询 SKU列表(状态为1)
			if(status.equals("1")){//审核通过
				List<TbItem> itemList = goodsAuditService.findItemListByGoodsIdAndStatus(ids, status);
				//调用搜索接口实现数据批量导入
				if(itemList.size()>0){
					final String jsonString = JSON.toJSONString(itemList);
					jmsTemplate.send(queueSolrDestination, new MessageCreator() {
						@Override
						public Message createMessage(Session session) throws JMSException {
							return session.createTextMessage(jsonString);
						}
					});
				}else{
					System.out.println("没有明细数据");
				}
				//静态页生成
				for(final Long goodsId : ids){
					jmsTemplate.send(topicPageDestination, new MessageCreator() {
						@Override
						public Message createMessage(Session session) throws JMSException {
							return session.createTextMessage(goodsId+"");
						}
					});
				}
			}
			actionResult = new ActionResult(true,"成功更新商品状态");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"失败更新商品状态");
		}
		return actionResult;
	}

	@RequestMapping("/delete")
	public ActionResult delete(final Long[] ids){
		logger.info(logStr+"delete方法");
		try {
			goodsAuditService.delete(ids);
			//删除solr缓存
			jmsTemplate.send(queueSolrDeleteDestination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createObjectMessage(ids);
				}
			});
			//删除web容器中的静态页面
			jmsTemplate.send(topicPageDeleteDestination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createObjectMessage(ids);
				}
			});
			actionResult = new ActionResult(true,"成功删除商品信息");
		}catch (NullPointerException e){
			e.printStackTrace();
			actionResult = new ActionResult(false,"失败删除商品信息");
		}
		return actionResult;
	}

}
