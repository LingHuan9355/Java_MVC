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
public class ActionServlet extends GenericServlet{

	Properties ps = new Properties();
	
	/**
	 * 初始化操作：获取哪些请求对应到哪个Action进行处理
	 * 找到可以被客户端请求的资源信息
	 */
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
         //接收客户端的请求信息
	     File file = new File(uri);
	     //获取访问资源
	     String path = file.getName();
	     //获取类名
	     String className = ps.getProperty(path);
	     
	     try {
	    	//创建类对象
			Class c = Class.forName(className);
			//获取Action的对象
		    Action action = (Action) c.newInstance();
		    
		    ActionForward forward = action.execute(request, response);
		    //利用自定义的ActionForward进行jsp或action的跳转
		    forward.forward(request, response);
	     
	     } catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		 }
	     
	}

}
