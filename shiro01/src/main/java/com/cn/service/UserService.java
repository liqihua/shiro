package com.cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.UserDao;
import com.cn.pojo.User;

@Service("UserService")
@Transactional
public class UserService {
	@Resource
	private UserDao userDao;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public User findByName(String username){
		return userDao.findByName(username);
	}
	
}
