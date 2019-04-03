package com.plusmall.user;

import com.plusmall.commons.PageResult;
import com.plusmall.model.TbAddress;

import java.util.List;

/**
 * @Description:
 */
public interface AddressService {
	public List<TbAddress> findAll();
	public PageResult findPage(int pageNum, int pageSize);
	public void add(TbAddress address);
	public void update(TbAddress address);
	public TbAddress findOne(Long id);
	public void delete(Long[] ids);
	public PageResult findPage(TbAddress address, int pageNum, int pageSize);
	public List<TbAddress> findListByUserId(String userId);
}
