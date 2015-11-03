package com.cn.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.pojo.Role;
import com.cn.pojo.User;

@Service
@Transactional
public class MyShiro extends AuthorizingRealm{
	@Inject  
    private UserService userService; 
	
	/** 
     * 权限认证 
     */  
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//获取登录时输入的用户名  
        String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();  
        //到数据库查是否有此对象  
        User user=userService.findByName(loginName);  
        if(user!=null){  
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();  
            //用户的角色集合  
            info.setRoles(user.getRolesName());  
            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要  
            List<Role> roleList=user.getRoleList();  
            for (Role role : roleList) {  
                info.addStringPermissions(role.getPermissionsName());  
            }  
            return info;  
        }  
		
		return null;
	}
	
	 /** 
     * 登录认证; 
     */  
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		
		
		return null;
	}
	
}
