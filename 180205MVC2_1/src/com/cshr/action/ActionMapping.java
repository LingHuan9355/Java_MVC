package com.cshr.action;

/**
 * 
 *<p>Title:ActionMapping </p>
 *Description: 
 *��������ڻ�ȡconfig/config.xml������
 * ��action��ǩ�е�������Ϣ
 *@author LH
 *@date 2018-2-4����04:35:59
 *@version V1.0
 */
public class ActionMapping {

	//pathΪ�ͻ��˵�����·��
	private String path;
	//typeΪ��Ӧ��Action��
	private String type;
	//nameΪ��Ӧ��ʵ�������
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
