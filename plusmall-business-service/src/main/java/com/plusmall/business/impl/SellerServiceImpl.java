package com.plusmall.business.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.business.SellerService;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbSellerMapper;
import com.plusmall.model.TbSeller;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Description:
 */
@Service
public class SellerServiceImpl implements SellerService {
	private static Logger logger = Logger.getLogger(SellerServiceImpl.class);
	private static String logStr = "进入到SellerServiceImpl-";

	@Autowired
	private TbSellerMapper sellerMapper;

	@Override
	public PageResult search(int pageNum, int pageSize, TbSeller seller) throws NullPointerException {
		return null;
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {

	}

	@Override
	public void add(TbSeller seller) throws NullPointerException {
		logger.info(logStr+"add方法");
		seller.setStatus("0");
		seller.setCreateTime(new Date());
		sellerMapper.insert(seller);
	}

	@Override
	public TbSeller findOne(Long id) {
		return null;
	}

	@Override
	public void update(TbSeller seller) throws NullPointerException {

	}
}
