package com.plusmall.business.login;

import com.alibaba.dubbo.config.annotation.Reference;
import com.plusmall.business.SellerService;
import com.plusmall.model.TbSeller;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	private static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	private static String logStr = "经过了UserDetailsServiceImpl-";
	private SellerService sellerService;

	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(logStr+"loadUserByUsername方法");
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		try {
			username = new String(username.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		TbSeller seller = sellerService.findOne(username);
		if (seller != null){
			if (seller.getStatus().equals("1")){
				return new User(username,seller.getPassword(),grantedAuths);
			}else {
				return null;
			}
		}else {
			return null;
		}
	}
}
