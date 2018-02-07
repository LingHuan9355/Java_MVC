package com.cshr.action;

/**
 * 
 *<p>Title:ActionMapping </p>
 *Description: 
 *这个类用于获取config/config.xml中配置
 * 的action标签中的属性信息
 *@author LH
 *@date 2018-2-4下午04:35:59
 *@version V1.0
 */
public class ActionMapping {

	//path为客户端的请求路径
	private String path;
	//type为对应的Action类
	private String type;
	//name为对应的实体类对象
	private String name;
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
