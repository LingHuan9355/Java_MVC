package com.cshr.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionForward {

	private String url;
	//false表示请求转发 true表示重定向
	private boolean redirect;
	
	/*public ActionForward() {
		super();
	}*/

	public ActionForward(String url) {
		super();
		this.url = url;
	}
	
	public ActionForward(String url, boolean redirect) {
		super();
		this.url = url;
		this.redirect = redirect;
	}
	

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	public void forward(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		if(redirect){
			response.sendRedirect(url);
		}else{
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
	
}
