package com.cshr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cshr.dao.UserInfoDao;
import com.cshr.entity.UserInfo;

public class SelectOneAction implements Action {

	public ActionForward execute(ActionForm actionForm,HttpServletRequest request,
			HttpServletResponse response) {
		
		int uid = Integer.parseInt(request.getParameter("updateId"));
		System.out.println(uid);
		
		//UserInfo userInfo = (UserInfo) actionForm;
		Object obj = UserInfoDao.selectById(UserInfo.class, uid);
		UserInfo user = (UserInfo) obj;
		//System.out.println(user.getId()+"\t"+user.getName()+"\t"+user.getPwd()+"\t"+user.getBrithday());
		
		request.setAttribute("userInfo", user);
		
		return new ActionForward("updateUser.jsp", false);
	}

}
