package com.cshr.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cshr.action.Action;
import com.cshr.action.ActionForm;
import com.cshr.action.ActionForward;
import com.cshr.action.ActionMapping;
import com.cshr.utils.BeanUtils;

@SuppressWarnings("serial")
public class ActionServlet extends HttpServlet {
      
	//Properties ps = new Properties(); 
	//自定义的map对象，用于装多个ActionMapping
	private Map<String,ActionMapping> moudleConfig = new HashMap<String, ActionMapping>();
	
	@Override
	public void init() throws ServletException {

		/*InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/config/actionConfig.properties");
		try {
			ps.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/config/config.xml");   
		    Document doc = db.parse(is);
		
		  //根据标签名获取action的节点
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
			
			  //以请求的路径为key，以actionmapping对象为值
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

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	     // System.out.println(moudleConfig.size());	
	     
		 //获取客户端的uri
		 String uri = request.getRequestURI();
		 ///180205MVC2_1/addUser.action
		 //System.out.println(uri);
		 
		 File file = new File(uri);
		 
		 String path = file.getName();
		 //addUser.action
		// System.out.println(path);
		//根据path
		 ActionMapping mapping = moudleConfig.get(path);
		//获取path对应的type
		 String type = mapping.getType();
		//获取收集表单数据的实体类对象(ActionForm)
		 String name = mapping.getName();
		 
		 try {
			//获取Action的类的对象
			Class c = Class.forName(type);
			//获取ActionForm的类的对象
		    Class c2 = Class.forName(name);
		  //获取类对象，创建出action的对象
		    Action action = (Action) c.newInstance();
		  //创建出收集表单数据的ActionForm对象
		    ActionForm actionForm = (ActionForm) c2.newInstance();
		    
		    Map<String, String>  parameters = new HashMap<String, String>();
 		    
		  //获取表单中所有表单控件的name属性值
		    Enumeration<String> ems = request.getParameterNames();
		    while(ems.hasMoreElements()){
		    	//表单控件的name
		    	String parameterName = ems.nextElement();
		    	//表单控件的value
		    	String parameterValue = request.getParameter(parameterName);
		        parameters.put(parameterName, parameterValue);
		    	//System.out.println(parameterName+"\t"+parameterValue);
		    
		    }
		    
		  //调用工具类，把map中的值封装到ActionForm中
		    BeanUtils.populate(actionForm, parameters);
		    //执行exectue方法
		    ActionForward forward = action.execute(actionForm,request, response);
		  //跳转视图
		    forward.forward(request, response);
		    
		 
		 } catch (Exception e) {
			e.printStackTrace();
		}
 		 
		
	}
	
	
	
	/*@SuppressWarnings("unchecked")
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
	    
	}*/

}
