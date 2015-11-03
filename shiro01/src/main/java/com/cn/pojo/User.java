package com.cn.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="user",catalog="myshiro")
public class User {
	private Integer id;
	@NotEmpty(message="�û�������Ϊ��")
	private String username;
	@NotEmpty(message="���벻��Ϊ��")
	private String password;
	private List<Role> roleList;	//һ���û����Ծ��ж����ɫ
	
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToMany  
	@JoinTable(name="user_role",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="role_id")})  
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	
	@Transient  
    public Set<String> getRolesName(){  
        List<Role> roles=getRoleList();  
        Set<String> set=new HashSet<String>();  
        for (Role role : roles) {  
            set.add(role.getRolename());  
        }  
        return set;  
    }  
	
	
}
