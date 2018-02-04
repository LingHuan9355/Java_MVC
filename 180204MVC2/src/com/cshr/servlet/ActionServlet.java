package com.cshr.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cshr.action.Action;
import com.cshr.action.ActionForm;
import com.cshr.action.ActionForward;
import com.cshr.action.ActionMapping;
import com.cshr.utils.BeanUtils;

/**
 * 
 *<p>Title:ActionServlet </p>
 *Description: 
 *MyStruts�ĺ��Ŀ�����
 *@author LH
 *@date 2018-2-4����04:29:39
 *@version V1.0
 */
@SuppressWarnings("serial")
public class ActionServlet extends HttpServlet {

	//�Զ����map��������װ���ActionMapping
	private Map<String,ActionMapping> moudleConfig = new HashMap<String,ActionMapping>();

	/**
	 * ��ʼ��ʱ�����mystruts.xml����Ϣ
	 */
	@Override
	public void init() throws ServletException {
	    try {
       	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/config/mystruts.xml");
            Document doc =  db.parse(is);
	       
            //���ݱ�ǩ����ȡaction�Ľڵ�
            NodeList nodeList = doc.getElementsByTagName("action");
            for (int i = 0; i < nodeList.getLength(); i++) {
				Element node = (Element) nodeList.item(i);
				String path = node.getAttribute("path");
				String type = node.getAttribute("type");
				String name = node.getAttribute("name");
				
				ActionMapping mapping = new ActionMapping();
				mapping.setPath(path);
				mapping.setType(type);
				mapping.setName(name);
		
				//�������·��Ϊkey����actionmapping����Ϊֵ
				moudleConfig.put(path, mapping);
			}
	    
	    } catch (Exception e) {
			e.printStackTrace();
		}
	     
	
	}
	
	
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
           doPost(req, resp);
	 }
	 
	 @SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 	//System.out.println(moudleConfig.size());	
	 
		//��ȡ�ͻ��˵�uri
		//  /180204MVC2/reg.do
		 String uri = request.getRequestURI();
		 //System.out.println(uri);
		 File file = new File(uri);
		 String path = file.getName();
		 //reg.do
		// System.out.println(path);
		
		 //����path
		 ActionMapping mapping = moudleConfig.get(path);
		//��ȡpath��Ӧ��type
		 String type = mapping.getType();
		//��ȡ�ռ������ݵ�ʵ�������(ActionForm)		 
         String name = mapping.getName();	    
		 try {
	    	//��ȡAction����Ķ���
			Class c = Class.forName(type);
			//��ȡActionForm����Ķ���
            Class c2 = Class.forName(name);
			//��ȡ����󣬴�����action�Ķ���
			Action action = (Action) c.newInstance();
			//�������ռ������ݵ�ActionForm����
			ActionForm actionForm = (ActionForm) c2.newInstance();
			
			Map<String, String> parameters = new HashMap<String, String>();
			
			//��ȡ�������б��ؼ���name����ֵ
			Enumeration<String> ems = request.getParameterNames();
			while(ems.hasMoreElements()){
				//���ؼ���name
				String parameterName = ems.nextElement();
				//���ؼ���value
				String parameterValue = request.getParameter(parameterName);
				
				parameters.put(parameterName, parameterValue);
				//System.out.println(parameterName+"\t"+parameterValue);
				
			}
			
			//���ù����࣬��map�е�ֵ��װ��ActionForm��
			BeanUtils.populate(actionForm, parameters);
			//ִ��execute����
		    ActionForward forward = action.execute(actionForm, request, response);
			//��ת��ͼ
			forward.forward(request, response);
			
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 
	 }
}
