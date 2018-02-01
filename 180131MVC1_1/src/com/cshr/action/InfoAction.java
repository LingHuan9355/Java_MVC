package com.cshr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		
		System.out.println(name+"\t" + sex+"\t"+address);
		
		
		request.getSession().setAttribute("name", name);
		request.getSession().setAttribute("address", address);
		request.getSession().setAttribute("sex", sex);

		if("ÄÐ".equalsIgnoreCase(sex)){
			return (new ActionForward("man.jsp",false));
		}else{
			return (new ActionForward("woman.jsp", false));
		}
	}

}
