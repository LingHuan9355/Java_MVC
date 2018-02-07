package com.cshr.entity;

import java.util.Date;

import com.cshr.action.ActionForm;

/**
 * 
 *<p>Title:UserInfo </p>
 *Description: 
 *Ҫ��ʵ����һ��Ҫ�ͱ���һ��
 *�������ͱ������һ��
 *@author LH
 *@date 2018-2-1����04:14:43
 *@version V1.0
 */
public class UserInfo extends ActionForm{
	
	   /*id int auto_increment primary key ,
	   name varchar(30) not null,
	   pwd varchar(30) not null,
	   brithday date not null */
	
	 private int id;
	 private String name;
	 private String pwd;
	 private Date brithday;
	 
	public UserInfo() {
		super();
	}
	
	public UserInfo(String name, String pwd, Date brithday) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.brithday = brithday;
	}

	public UserInfo(int id, String name, String pwd, Date brithday) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.brithday = brithday;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	 
	 
	
}
