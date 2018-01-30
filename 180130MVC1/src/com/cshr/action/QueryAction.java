package com.cshr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cshr.dao.BaseDao;
import com.cshr.entity.Student;

public class QueryAction implements Action{

	@SuppressWarnings("unchecked")
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) {
		
		//调用basedao的通用查询方法，获取student表的信息
		List<Student> list= BaseDao.find(Student.class);
		req.setAttribute("slist", list);
		
		return new ActionForward("slist.jsp");
	}

}
