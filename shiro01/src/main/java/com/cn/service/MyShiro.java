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
     * Ȩ����֤ 
     */  
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//��ȡ��¼ʱ������û���  
        String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();  
        //�����ݿ���Ƿ��д˶���  
        User user=userService.findByName(loginName);  
        if(user!=null){  
            //Ȩ����Ϣ����info,������Ų�����û������еĽ�ɫ��role����Ȩ�ޣ�permission��  
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();  
            //�û��Ľ�ɫ����  
            info.setRoles(user.getRolesName());  
            //�û��Ľ�ɫ��Ӧ������Ȩ�ޣ����ֻʹ�ý�ɫ�������Ȩ�ޣ���������п��Բ�Ҫ  
            List<Role> roleList=user.getRoleList();  
            for (Role role : roleList) {  
                info.addStringPermissions(role.getPermissionsName());  
            }  
            return info;  
        }  
		
		return null;
	}
	
	 /** 
     * ��¼��֤; 
     */  
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		
		
		return null;
	}
	
}
