package com.plusmall.user;

import com.plusmall.model.TbUser;

/**
 * @Description:
 */
public interface UserService {
	public void add(TbUser user) throws NullPointerException;
	public void createSmsCode(String phone);
	public boolean checkSmsCode(String phone,String code);
}
