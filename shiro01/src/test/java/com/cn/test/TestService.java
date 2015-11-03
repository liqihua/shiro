package com.cn.test;


import org.junit.Test;

import com.cn.service.UserService;

public class TestService {
	
	
	@Test
	public void testFindByName(){
		UserService userServcie = new UserService();
		userServcie.findByName("tom");
	}
	
}
