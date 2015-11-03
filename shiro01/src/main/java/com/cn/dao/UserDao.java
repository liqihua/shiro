package com.cn.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import sun.reflect.generics.tree.BaseType;

import com.cn.pojo.User;

public class UserDao extends HibernateDaoSupport{

	public User findByName(String username){
		String hql = " from User where username=?";
		return (User)this.getSession().createQuery(hql).setString(0, username).uniqueResult();
	}
	
}
