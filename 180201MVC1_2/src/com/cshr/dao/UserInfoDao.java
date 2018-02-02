package com.cshr.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInfoDao {

	
	//添加用户信息
	@SuppressWarnings("unchecked")
	public static int addUserInfo(Object obj){
		int row = 0;
		
		Class c = obj.getClass();
		Field [] fs = c.getDeclaredFields();
		Field.setAccessible(fs,true);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into ");
		sb.append(c.getSimpleName());
		sb.append(" ( ");
		for (int i = 1; i < fs.length; i++) {
			sb.append(fs[i].getName());
			if(i < fs.length-1){
				sb.append(" ,");
			}
		}
		sb.append(" ) ");
		sb.append(" values ( ");
		for (int i = 1; i < fs.length; i++) {
			sb.append(" ? ");
			if(i < fs.length-1){
				sb.append(" , ");
			}
		}
		
		sb.append(" ) ");
	    //System.out.println(sb);
		
	    Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = BaseDao.getConn();
			pst = conn.prepareStatement(sb.toString());
			for (int i = 1; i < fs.length; i++) {
				 if(fs[i].get(obj) instanceof java.util.Date){
				      java.util.Date date = (Date) fs[i].get(obj);
				      Date sdate = new Date(date.getTime());
				      pst.setObject(i, sdate);
				 }else{
					  pst.setObject(i, fs[i].get(obj));
				 }
			}
			row = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, pst, conn);
		}
		
		return row;
	}

	//查询用户信息
	@SuppressWarnings("unchecked")
	public static List showUser(Class c){
		List list = new ArrayList();
	   
		Field [] fs = c.getDeclaredFields();
	    Field.setAccessible(fs,true);
	 
	    StringBuffer sb = new StringBuffer();
	    sb.append(" select * from ");
	    sb.append(c.getSimpleName());
	    //System.out.println(sb);
	    
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    
	    try {
	    	conn = BaseDao.getConn();
			pst = conn.prepareStatement(sb.toString());
		    rs = pst.executeQuery();
	        while(rs.next()){
	        	Object obj = c.newInstance();
	        	for (int i = 0; i < fs.length; i++) {
					if(rs.getObject(fs[i].getName()) instanceof BigDecimal){
						BigDecimal big = (BigDecimal) rs.getObject(fs[i].getName());
						int value = big.intValue();
						fs[i].set(obj, value);
					}else if(rs.getObject(fs[i].getName()) instanceof Timestamp){
				        Timestamp time = (Timestamp) rs.getObject(fs[i].getName());
				        Date date = new Date(time.getTime());
				        fs[i].set(obj, date);
					}else{
						fs[i].set(obj, rs.getObject(fs[i].getName()));
					}
				}
	        	list.add(obj);
	        }
	    } catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(rs, pst, conn);
		}
	    
	    return list;
	}

    //查询单个用户信息
	@SuppressWarnings("unchecked")
	public static Object selectById(Class c,int id){
         Object obj = null;
         Field [] fs = c.getDeclaredFields();
         Field.setAccessible(fs,true);
         
         StringBuffer sb = new StringBuffer();
         sb.append(" select * from ");
         sb.append(c.getSimpleName());
         sb.append(" where ");
         sb.append(fs[0].getName());
         sb.append(" = ? ");
        // System.out.println(sb);
         
         Connection conn = null;
         PreparedStatement pst = null;
         ResultSet rs = null;
         
         try {
   		     obj = c.newInstance(); 
        	 conn = BaseDao.getConn();
			 pst = conn.prepareStatement(sb.toString());
		     pst.setObject(1,id);
		     rs = pst.executeQuery();
		     while(rs.next()){
		    	 for (int i = 0; i < fs.length; i++) {
	        		   if(rs.getObject(fs[i].getName()) instanceof BigDecimal){
							BigDecimal big = (BigDecimal) rs.getObject(fs[i].getName());
							int value = big.intValue();
							fs[i].set(obj, value);
						}else if(rs.getObject(fs[i].getName()) instanceof Timestamp){
							Timestamp time = (Timestamp) rs.getObject(fs[i].getName());
							Date date = new Date(time.getTime());
							fs[i].set(obj, date);
						}else{
							//stu.setId(rs.getInt("id"));
							//stu.setName(rs.getString("name"));
							fs[i].set(obj, rs.getObject(fs[i].getName()));
						}
				    }
		     }
         
         } catch (Exception e) {
			e.printStackTrace();
		 }finally{
			BaseDao.closeAll(rs, pst, conn);
		 }
         
         return obj;
	}

    //修改用户信息
	@SuppressWarnings("unchecked")
	public static int updateUser(Object obj){
		int row = 0;
	    Class c = obj.getClass();
	    Field [] fs = c.getDeclaredFields();
	    Field.setAccessible(fs, true);
	    
	    StringBuffer sb = new StringBuffer();
	    sb.append(" update ");
	    sb.append(c.getSimpleName());
	    sb.append(" set ");
	    for (int i = 1; i < fs.length; i++) {
			sb.append(fs[i].getName());
			sb.append(" = ? ");
			if(i < fs.length-1){
				sb.append(", ");
			}
		}
	    sb.append(" where ");
	    sb.append(fs[0].getName());
	    sb.append(" = ? ");
	    //System.out.println(sb);	
	    
	    Connection conn = null;
	    PreparedStatement pst = null;
	    
	    try {
	    	conn = BaseDao.getConn();
			pst = conn.prepareStatement(sb.toString());
			for (int i = 1; i < fs.length; i++) {
				if(fs[i].get(obj) instanceof BigDecimal){
					BigDecimal big = (BigDecimal) fs[i].get(obj);
					int value = big.intValue();
					pst.setObject(i, value);
					
				}else if(fs[i].get(obj) instanceof java.util.Date){
                    java.util.Date date =(Date) fs[i].get(obj);
                    Date sdate = new Date(date.getTime());
                    pst.setObject(i,sdate);
					
				}else{
					
					pst.setObject(i, fs[i].get(obj));
				}
			}
			
			pst.setObject(fs.length, fs[0].get(obj));
			row = pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, pst, conn);
		}
		
		return row;
	}

	//删除用户信息
	@SuppressWarnings("unchecked")
	public static int deleteUserById(Class c,int id){
		int row = 0;
		
		Field [] fs = c.getDeclaredFields();
		Field.setAccessible(fs,true);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from ");
		sb.append(c.getSimpleName());
		sb.append(" where ");
		sb.append(fs[0].getName());
		sb.append(" = ? ");
	    //System.out.println(sb);
		
	    Connection conn = null;
	    PreparedStatement pst = null;
	    
	    try {
	    	conn = BaseDao.getConn();
			pst = conn.prepareStatement(sb.toString());
            pst.setObject(1,id);
            
            row = pst.executeUpdate();
	    } catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.closeAll(null, pst, conn);
		}
	    
		return row;
	}
	
	
	
	
}
