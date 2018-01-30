package com.cshr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	public /*String*/ ActionForward execute(HttpServletRequest req,HttpServletResponse res);
}
