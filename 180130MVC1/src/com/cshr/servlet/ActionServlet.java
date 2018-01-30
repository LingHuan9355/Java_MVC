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
	
	/**
	 * ��ʼ����������ȡ��Щ�����Ӧ���ĸ�Action���д���
	 * �ҵ����Ա��ͻ����������Դ��Ϣ
	 */
	@Override
	public void init() throws ServletException {
        ServletContext context = this.getServletContext();
        InputStream is = context.getResourceAsStream("/WEB-INF/config/config.properties");
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
          
        //���տͻ��˵�������Ϣ
          String uri = request.getRequestURI();
          ///mvc1/neike.do
         // System.out.println(uri);
          //�����ļ����󣬻�ȡ��neike.do
          File file = new File(uri);
          //��ȡ������Դ
          String path = file.getName();
         // System.out.println(path);
          //��ȡ����
          String className = ps.getProperty(path);
         // System.out.println(className);
          
          try {
        	//���������
			Class c = Class.forName(className);
			//��ȡAction�Ķ���
            Action action = (Action) c.newInstance();
            //1.Actionִ��execute��Ϊ�������ؽ��
            //String result = action.execute(request, response);
            //response.sendRedirect(result);
          
            ActionForward forward = action.execute(request, response);
            
            /*//2.�ж�
            if(forward.isRedirect()){
            	//ִ���ض���
            	response.sendRedirect(forward.getUrl());
            }else{
            	//����ת��
            	request.getRequestDispatcher(forward.getUrl()).forward(request, response);
            }*/
            
            //3.�����Զ����ActionForward����jsp��action����ת
            forward.forward(request, response);
            
          } catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		  }
	}

}