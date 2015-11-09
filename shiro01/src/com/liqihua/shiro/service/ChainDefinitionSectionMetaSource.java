package com.liqihua.shiro.service;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;

import com.liqihua.shiro.bean.Permission;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{
	
	@Resource
	private UserService userService;
	
	private String filterChainDefinitions;		//默认的url，配置文件中的
    public static final String PREMISSION_STRING="perms[\"{0}\"]"; 		//默认premission字符串 

	@Override
	public Section getObject() throws Exception {
		List<Permission> list = userService.findAllPermission();
		Ini ini = new Ini();
		ini.load(filterChainDefinitions); 		//加载默认的url
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		if(list != null){
			for(Permission p : list){
				if(StringUtils.isNotBlank(p.getUrl()) && StringUtils.isNotBlank(p.getPermissionname())){
					section.put(p.getUrl(), MessageFormat.format(PREMISSION_STRING, p.getPermissionname()));
				}
			}
		}
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

}
