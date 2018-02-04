package com.cshr.entity;

import java.sql.Date;

import com.cshr.action.ActionForm;

/**
 * 
 *<p>Title:Student </p>
 *Description: 
 *要求：实体类一定要和表名一致
 *属性名和表的列名一致
 *@author LH
 *@date 2018-1-27下午06:31:16
 *@version V1.0
 */
public class Student  extends ActionForm{
	/* id number primary key ,
	  name varchar2(20) not null,
	  sex varchar2(2) not null,
	  brithday date not null*/
	private int id;
	private String name;
	private Double momey;
	private Date brithday;
	
	
	
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
	public Double getMomey() {
		return momey;
	}
	public void setMomey(Double momey) {
		this.momey = momey;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	@Override
	public String toString() {
		return "Student [brithday=" + brithday + ", id=" + id + ", momey="
				+ momey + ", name=" + name + "]";
	}

	

}
