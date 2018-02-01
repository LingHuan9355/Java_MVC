package com.cshr.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	public void destroy() {

	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
           HttpServletRequest request = (HttpServletRequest) req;
           HttpServletResponse response = (HttpServletResponse) res;
		
           String method = request.getMethod();
           System.out.println(method);
           if("GET".equalsIgnoreCase(method)){
        	   Enumeration<String> ems = request.getAttributeNames();
        	   while(ems.hasMoreElements()){
        		   String name = ems.nextElement();
        		   String [] values = request.getParameterValues(name);
        		   if(values != null){
                        for (int i = 0; i < values.length; i++) {
							values[i]  = new String(values[i].getBytes("iso-8859-1"),"utf-8");
						}
        		   }
        	   }
        	   
           }else if("POST".equalsIgnoreCase(method)){
        	   request.setCharacterEncoding("utf-8");   
           }
           
           response.setContentType("text/html;charset=utf-8");
           chain.doFilter(request, response);
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
