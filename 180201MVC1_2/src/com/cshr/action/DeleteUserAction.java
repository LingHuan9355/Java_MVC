package com.cshr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cshr.dao.UserInfoDao;
import com.cshr.entity.UserInfo;

public class DeleteUserAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		int did = Integer.parseInt(request.getParameter("deleteId"));
		//System.out.println(did);
		
		int row = UserInfoDao.deleteUserById(UserInfo.class, did);
		if(row > 0){
			
			return new ActionForward("showUser.action", true);
		}else{
			
			return new ActionForward("error.jsp", true);
		}
		
	}

}
