package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbSellerMapper;
import com.plusmall.model.TbSeller;
import com.plusmall.model.TbSellerExample;
import com.plusmall.operator.SellerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 */
@Service
public class SellerServiceImpl implements SellerService {
	private static Logger logger = Logger.getLogger(SellerServiceImpl.class);
	private static String logStr = "进入SellerServiceImpl-";

	@Autowired
	private TbSellerMapper sellerMapper;

	@Override
	public PageResult search(int pageNum, int pageSize, TbSeller seller){
		logger.info(logStr+"进入search方法");
		PageHelper.startPage(pageNum,pageSize);
		TbSellerExample example = new TbSellerExample();
		TbSellerExample.Criteria criteria = example.createCriteria();
		if (seller.getName() != null && seller.getName().length() > 0){
			criteria.andNameLike("%"+seller.getName()+"%");		//公司名称
		}
		if (seller.getNickName() != null && seller.getNickName().length() > 0){
			criteria.andNickNameLike("%"+seller.getNickName()+"%");	//店铺名称
		}
		if (seller.getStatus() != null && seller.getStatus().length() > 0){
			criteria.andStatusEqualTo(seller.getStatus());
		}
		Page<TbSeller> page = (Page<TbSeller>) sellerMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {

	}

	@Override
	public void add(TbSeller seller) throws NullPointerException {

	}

	@Override
	public TbSeller findOne(String sellerId) {
		logger.info(logStr+"findOne方法");
		return sellerMapper.selectByPrimaryKey(sellerId);
	}

	@Override
	public void udpate(TbSeller seller) throws NullPointerException {
		logger.info(logStr+"update方法");
		sellerMapper.updateByPrimaryKey(seller);
	}

	@Override
	public void updateStatus(String strId, String status) {
		logger.info(logStr+"updateStatus方法");
		TbSeller seller = sellerMapper.selectByPrimaryKey(strId);
		seller.setStatus(status);
		sellerMapper.updateByPrimaryKey(seller);
	}
}
