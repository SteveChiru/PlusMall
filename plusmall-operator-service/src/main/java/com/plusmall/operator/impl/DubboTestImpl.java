package com.plusmall.operator.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.plusmall.operator.DubboTest;

/**
 * @Description:
 */
@Service
public class DubboTestImpl implements DubboTest {
	@Override
	public String getName() {
		return "Mary";
	}
}
