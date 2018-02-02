package com.cshr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cshr.dao.UserInfoDao;
import com.cshr.entity.UserInfo;

public class ShowUserAction implements Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<UserInfo> list = UserInfoDao.showUser(UserInfo.class);
		/*for (UserInfo ui : list) {
			System.out.println(ui.getName()+ "\t"+ ui.getPwd()+"\t"+ui.getBrithday());
		}*/
		
		request.setAttribute("slist", list);
		
		return new ActionForward("showUser.jsp", false);
	}

}
