package com.plusmall.user.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.plusmall.mapper.TbUserMapper;
import com.plusmall.model.TbUser;
import com.plusmall.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private String logStr = "进入UserServiceImpl-";

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination smsDestination;

	@Value("${template_code}")
	private String template_code;

	@Value("${sign_name}")
	private String sign_name;


	@Override
	public void add(TbUser user) throws NullPointerException {
		logger.info(logStr+"add方法");
		user.setCreated(new Date());
		user.setUpdated(new Date());
		String password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(password);
		userMapper.insert(user);
	}

	@Override
	public void createSmsCode(final String phone) {
		logger.info(logStr+"createSmsCode方法");
		final String code = (long)(Math.random()*1000000)+"";
		logger.info("验证码："+code);
		//存入缓存
		redisTemplate.boundHashOps("smscode").put(phone,code);
		//发送activeMQ
		jmsTemplate.send(smsDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("mobile",phone);
				mapMessage.setString("template_code",template_code);
				mapMessage.setString("sign_name",sign_name);
				Map m = new HashMap();
				m.put("number",code);
				mapMessage.setString("param",JSON.toJSONString(m));
				return mapMessage;
			}
		});
	}

	@Override
	public boolean checkSmsCode(String phone, String code) {
		logger.info(logStr+"checkSmsCode方法");
		String sysCode = (String) redisTemplate.boundHashOps("smscode").get(phone);
		if (sysCode == null){
			return false;
		}
		if (!sysCode.equals(code)){
			return false;
		}
		return true;
	}
}
