package com.cshr.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	public ActionForward execute(ActionForm actionForm, HttpServletRequest request,HttpServletResponse response);
}
