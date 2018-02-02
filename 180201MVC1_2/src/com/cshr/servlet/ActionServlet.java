package com.cshr.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cshr.action.Action;
import com.cshr.action.ActionForward;

@SuppressWarnings("serial")
public class ActionServlet extends GenericServlet {

	Properties ps = new Properties(); 
	
	@Override
	public void init() throws ServletException {

		ServletContext context = this.getServletContext();
		InputStream is = context.getResourceAsStream("/WEB-INF/config/actionConfig.properties");
		try {
			ps.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String uri = request.getRequestURI();
		///180201MVC1_2/addUser.action
		//System.out.println(uri);
		
		File file = new File(uri);
		
		String path = file.getName();
		//addUser.action
		//System.out.println(path);

		String className = ps.getProperty(path);
		//com.cshr.action.AddUserAction
	    //System.out.println(className);
	    
	    try {
			Class c = Class.forName(className);
			Action action =(Action) c.newInstance();
			ActionForward forward = action.execute(request, response);
			forward.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	    
	}

}
