package com.cshr.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cshr.dao.UserInfoDao;
import com.cshr.entity.UserInfo;

public class AddUserAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String brithday = request.getParameter("brithday");
		//System.out.println(name+"\t"+pwd+"\t"+brithday);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date date;
		int row = 0;
	
		try {
				date = sdf.parse(brithday);
				Object obj = new UserInfo(name,pwd,date);
				row = UserInfoDao.addUserInfo(obj);
		} catch (ParseException e) {
				e.printStackTrace();
		}
		
		if(row > 0){
			
		    return new ActionForward("success.jsp", true);
		}else {
			
		    return new ActionForward("error.jsp", true);
		}
		

	}

}
