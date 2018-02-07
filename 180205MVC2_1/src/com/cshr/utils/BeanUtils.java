package com.cshr.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class BeanUtils {

	@SuppressWarnings("unchecked")
	public static void populate(Object obj,Map<String,String> parameters){
		try {
		
			//获取类对象
			Class c = obj.getClass();
			//解析MAP
			Iterator<Map.Entry<String, String>> iterator = parameters.entrySet().iterator();
			
			while (iterator.hasNext()) {
	            Map.Entry<String, String> entry = iterator.next();
	            String parameterName = entry.getKey();
	            String parameterValue = entry.getValue();
	            
	            //获取ActionForm中的属性对象
				Field field = c.getDeclaredField(parameterName);
				field.setAccessible(true);

				//获取属性的类型
				String fieldType = field.getType().getName();
				if("java.util.Date".equals(fieldType)){
					//String-->java.util.Date
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date udate = sdf.parse(parameterValue);
					field.set(obj, udate);
				}else if("java.sql.Date".equals(fieldType)){
					java.sql.Date sdate = java.sql.Date.valueOf(parameterValue);
					field.set(obj,sdate);
				}else{
					//String--->Integer Double Float Long
					//Integer it = new Integer(parameterValue);
					//Double db = new Double(parameterValue);
					//获取属性对象类型的Class
					Class ctype = field.getType();
					//Contructor ct = ctype.getConstructor(String.class) 拿到对应的构造函数
					//ct.newInstance(parameterVlaue);
					Object value = ctype.getConstructor(String.class).newInstance(parameterValue);
					field.set(obj, value);
				}
				
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 	
		
	}
}
