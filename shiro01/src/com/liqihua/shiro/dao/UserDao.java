package com.liqihua.shiro.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.liqihua.shiro.bean.Permission;
import com.liqihua.shiro.bean.User;


@Repository("userDao")
public class UserDao {
	@Resource
	private SessionFactory sessionFactory; 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public User findByName(String username){
		String hql = " from User where username=?";
		User user = (User)this.getSession().createQuery(hql).setString(0, username).uniqueResult();
		return user;
	}
	
	public List<User> list(){
		String hql = " from User";
		List<User> list = this.getSession().createQuery(hql).list();
		return list;
	}
	
	public List<Permission> findPermissionByRid(Integer roleId){
		String hql = " from Permission p where p.role.id=?";
		List<Permission> list = this.getSession().createQuery(hql).setInteger(0, roleId).list();
		return list;
	}
	
}
