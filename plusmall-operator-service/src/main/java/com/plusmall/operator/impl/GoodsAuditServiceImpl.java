package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plusmall.commons.PageResult;
import com.plusmall.mapper.TbGoodsMapper;
import com.plusmall.mapper.TbItemMapper;
import com.plusmall.model.TbGoods;
import com.plusmall.model.TbGoodsExample;
import com.plusmall.model.TbItem;
import com.plusmall.model.TbItemExample;
import com.plusmall.operator.GoodsAuditService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
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
	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public PageResult search(int pageNum, int pageSize, TbGoods tbGoods) {
		logger.info(logStr+"search方法");
		PageHelper.startPage(pageNum,pageSize);
		TbGoodsExample example = new TbGoodsExample();
		TbGoodsExample.Criteria criteria = example.createCriteria();
		if (tbGoods.getGoodsName()!=null && tbGoods.getGoodsName().length() > 0){
			criteria.andGoodsNameLike("%"+tbGoods.getGoodsName()+"%");
		}
		criteria.andIsDeleteIsNull();
		Page<TbGoods> page = (Page<TbGoods>) tbGoodsMapper.selectByExample(example);
		return new PageResult(page.getTotal(),page.getPages(),page.getPageSize(),page.getResult());
	}

	@Override
	public void updateStatus(Long[] ids, String status) throws NullPointerException {
		logger.info(logStr+"updateStatus方法");
		for (Long id: ids){
			TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(id);
			tbGoods.setAuditStatus(status);
			tbGoodsMapper.updateByPrimaryKey(tbGoods);
		}
	}

	@Override
	public void delete(Long[] ids) throws NullPointerException {
		logger.info(logStr+"delete方法");
		for (Long id: ids){
			TbGoods tbGoods = tbGoodsMapper.selectByPrimaryKey(id);	//逻辑删除
			tbGoods.setIsDelete("1");
			tbGoodsMapper.updateByPrimaryKey(tbGoods);
		}
	}

	@Override
	public List<TbItem> findItemListByGoodsIdAndStatus(Long[] goodsIds, String status) throws NullPointerException {
		logger.info(logStr+"findItemListByGoodsIdAndStatus方法");
		TbItemExample example=new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdIn(Arrays.asList(goodsIds));
		criteria.andStatusEqualTo(status);
		return itemMapper.selectByExample(example);
	}
}
