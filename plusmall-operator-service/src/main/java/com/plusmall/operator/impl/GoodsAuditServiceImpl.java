package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbGoodsMapper;
import com.plusmall.model.TbGoods;
import com.plusmall.model.TbGoodsExample;
import com.plusmall.operator.GoodsAuditService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description:
 */
@Service
public class GoodsAuditServiceImpl implements GoodsAuditService {
	private static Logger logger = Logger.getLogger(GoodsAuditServiceImpl.class);
	private static String logStr = "进入GoodsAuditServiceImpl-";
	private static PageResult pageResult;

	@Autowired
	private TbGoodsMapper tbGoodsMapper;

	@Override
	public PageResult search(int pageNum, int pageSize, TbGoods tbGoods) {
		logger.info(logStr+"search方法");
		PageHelper.startPage(pageNum,pageSize);
		TbGoodsExample example = new TbGoodsExample();
		TbGoodsExample.Criteria criteria = example.createCriteria();
		if (tbGoods.getGoodsName()!=null && tbGoods.getGoodsName().length() > 0){
			criteria.andGoodsNameLike("%"+tbGoods.getGoodsName()+"%");
		}
		Page<TbGoods> page = (Page<TbGoods>) tbGoodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}
}
