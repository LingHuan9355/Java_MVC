package com.cshr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NeikeAction implements Action {

	public /*String */ ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("�ڿ�ҽ��ִ�з�����Ϊ...");
		
		String uname= req.getParameter("uname");
		String uage = req.getParameter("uage");
		System.out.println(uname + "\t" +uage);
		req.getSession().setAttribute("uname", uname);
		//return "neikeok.jsp";
		//����һ���Զ���Ķ����������װ��
		//һ��url����һ���Ƿ��ض���
		//false��ʾ����ת��
	
	     return new ActionForward("neikeok.jsp");
	}

}
