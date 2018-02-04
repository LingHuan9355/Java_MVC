package com.cshr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cshr.entity.Student;

public class RegAction implements Action {

	public ActionForward execute(ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("regAction");
		/*String name = request.getParameter("name");
		String brithday = request.getParameter("brithday");
		String money = request.getParameter("money");
		System.out.println(name+"\t"+brithday+"\t"+money);*/
		
		Student stu = (Student) actionForm;
		System.out.println(stu.getName()+"\t"+stu.getMomey()+"\t"+stu.getBrithday());
		
		return new ActionForward("success.jsp", true);
	}

}
