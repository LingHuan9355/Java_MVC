package com.cshr.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response){
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		System.out.println(name + "\t" +pwd);
		request.getSession().setAttribute("name", name);
		if("admin".equalsIgnoreCase(name) && "admin".equalsIgnoreCase(pwd)){
			
			return new ActionForward("success.jsp", false);
		}else{
			return new ActionForward("error.jsp", false);
			
		}
		
	}




}
