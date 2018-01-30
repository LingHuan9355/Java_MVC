package com.cshr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NeikeAction implements Action {

	public /*String */ ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("内科医生执行服务行为...");
		
		String uname= req.getParameter("uname");
		String uage = req.getParameter("uage");
		System.out.println(uname + "\t" +uage);
		req.getSession().setAttribute("uname", uname);
		//return "neikeok.jsp";
		//返回一个自定义的对象，这里面封装了
		//一个url，和一个是否重定向
		//false表示请求转发
	
	     return new ActionForward("neikeok.jsp");
	}

}
