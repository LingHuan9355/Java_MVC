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
 *MyStruts的核心控制器
 *@author LH
 *@date 2018-2-4下午04:29:39
 *@version V1.0
 */
@SuppressWarnings("serial")
public class ActionServlet extends HttpServlet {

	//自定义的map对象，用于装多个ActionMapping
	private Map<String,ActionMapping> moudleConfig = new HashMap<String,ActionMapping>();

	/**
	 * 初始的时候加载mystruts.xml的信息
	 */
	@Override
	public void init() throws ServletException {
	    try {
       	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/config/mystruts.xml");
            Document doc =  db.parse(is);
	       
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
	 
	 @SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 	//System.out.println(moudleConfig.size());	
	 
		//获取客户端的uri
		//  /180204MVC2/reg.do
		 String uri = request.getRequestURI();
		 //System.out.println(uri);
		 File file = new File(uri);
		 String path = file.getName();
		 //reg.do
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
			
			Map<String, String> parameters = new HashMap<String, String>();
			
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
			//执行execute方法
		    ActionForward forward = action.execute(actionForm, request, response);
			//跳转视图
			forward.forward(request, response);
			
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 
	 }
}
