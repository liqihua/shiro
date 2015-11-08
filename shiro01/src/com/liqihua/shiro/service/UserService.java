package com.liqihua.shiro.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liqihua.shiro.bean.Permission;
import com.liqihua.shiro.bean.User;
import com.liqihua.shiro.dao.UserDao;


@Service("userService")
@Transactional
public class UserService {
	@Resource
	private UserDao userDao;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public User findByName(String username){
		return userDao.findByName(username);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<User> list(){
		return userDao.list();
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Permission> findPermissionByRid(Integer roleId){
		return userDao.findPermissionByRid(roleId);
	}
	
	
}
