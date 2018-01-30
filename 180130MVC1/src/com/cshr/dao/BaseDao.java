package com.cshr.dao;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 
 *<p>Title:BaseDao </p>
 *Description: 
 *
 *1.写一个通用的insert语句的方法public int save(Object o)
 *2.写一个通用的查询所有数据的方法  public List find(Class c)
 *3.写一个通用的查询某个类的单个数据的方法  public Object load(Class c,int id)
 *如果有时间实现下面的例子
 *写一个通用的修改的方法 和删除的方法
 *public int update(Object o)
 *public int delete(Class c,int id)
 *
 *@author LH
 *@date 2018-1-28下午06:26:17
 *@version V1.0
 */
public class BaseDao {

	public static Properties ps = new Properties();
	
	static {
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("com/cshr/conf/jdbc.properties");
		try {
			ps.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取连接数据库对象
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(ps.getProperty("driver"));
            conn = DriverManager.getConnection(ps.getProperty("url"),ps.getProperty("uname"),ps.getProperty("upwd"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//关闭连接释放资源
	public static void closeAll(ResultSet rs, Statement st,Connection conn){
			try {
			   if(rs != null){
				 rs.close();
			   }
			   if(st != null){
				   st.close();
			   }
			   if(conn != null){
				   conn.close();
			   }
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		System.out.println(getConn());
	}
	
	//通用的insert语句的方法
	@SuppressWarnings("unchecked")
	public static int save(Object obj){
		int num = 0;
		Class c =obj.getClass();
		Field [] fs = c.getDeclaredFields();
		Field.setAccessible(fs,true);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into ");
		sb.append(c.getSimpleName());
		sb.append(" ( ");
		for (int i = 1; i < fs.length; i++) {
			sb.append(fs[i].getName());
			if(i < fs.length-1){
				sb.append(" , ");
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
		System.out.println(sb);
		
		Connection conn = null;
		PreparedStatement pst = null;
		
		conn = BaseDao.getConn();
		try {
			pst = conn.prepareStatement(sb.toString());
            for (int i = 1; i < fs.length; i++) {
				if(fs[i].get(obj) instanceof java.util.Date){
					java.util.Date date = (Date) fs[i].get(obj);
					Date sdate = new Date(date.getTime());
					pst.setObject(i, sdate);
				}else{
					pst.setObject(i,fs[i].get(obj));
				}
			}
            num = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(null, pst, conn);
		}

		return num;
	}
	
	//通用的查询所有数据的方法 
	@SuppressWarnings("unchecked")
	public static List find(Class c){
		List list = new ArrayList();
	    
		Field [] fs = c.getDeclaredFields();
		Field.setAccessible(fs, true);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from ");
		sb.append(c.getSimpleName());
		System.out.println(sb);
		
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
						//stu.setId(rs.getInt("id"));
						//stu.setName(rs.getString("name"));
						fs[i].set(obj, rs.getObject(fs[i].getName()));
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pst, conn);
		}
		
		return list;
	}
	
	//通用的查询某个类的单个数据的方法
	@SuppressWarnings("unchecked")
	public static Object load(Class c,int id) {
		Object obj = null;
	   
	   Field [] fs = c.getDeclaredFields();
	   Field.setAccessible(fs, true);
	   
	   StringBuffer sb = new StringBuffer();
	   sb.append(" select * from ");
	   sb.append(c.getSimpleName());
	   sb.append(" where ");
	   sb.append(fs[0].getName());
	   sb.append(" = ");
	   sb.append(id);
	   System.out.println(sb);
	   
	   Connection conn = null;
	   PreparedStatement pst = null;
	   ResultSet rs = null;
	   try {
		  obj = c.newInstance(); 
		   conn = BaseDao.getConn();
     	   pst = conn.prepareStatement(sb.toString());
     	   rs = pst.executeQuery();
           while(rs.next()){
        	   for (int i = 1; i < fs.length; i++) {
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
		  closeAll(rs, pst, conn);
	  }
	   
	   return obj;
	}
	
	//通用的修改的方法
	@SuppressWarnings("unchecked")
	public static int update(Object obj){
		int num  = 0;
		
		Class c = obj.getClass();
		Field [] fs = c.getDeclaredFields();
		Field.setAccessible(fs,true);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" update ");
		sb.append(c.getSimpleName());
		sb.append(" set ");
		for (int i = 1; i < fs.length; i++) {
			sb.append(fs[i].getName());
			sb.append(" = ? ");
			if(i < fs.length -1 ){
				sb.append(" ,");
			}
		}
		sb.append(" where ");
		sb.append(fs[0].getName());
		sb.append(" = ?");
		System.out.println(sb);
		
		Connection conn = null;
		PreparedStatement pst =null;
		
		try {
			conn = BaseDao.getConn();
			pst = conn.prepareStatement(sb.toString());
			for (int i = 1; i < fs.length; i++) {
				if(fs[i].get(obj) instanceof java.util.Date){
                   java.util.Date date = (Date) fs[i].get(obj);
                   Date sdate = new Date(date.getTime());
				   pst.setObject(i, sdate);
				}else {
					pst.setObject(i,fs[i].get(obj));
				}
				
			}
			pst.setObject(fs.length,fs[0].get(obj));
			num = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(null, pst, conn);
		}
		
		
		return num;
	}

	//删除的方法
	@SuppressWarnings("unchecked")
	public static  int delete(Class c,int id){
		int num = 0;
        
		Field [] fs = c.getDeclaredFields();
		Field.setAccessible(fs,true);
		
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from ");
		sb.append(c.getSimpleName());
		sb.append(" where ");
		sb.append(fs[0].getName());
		sb.append(" = ? ");
		System.out.println(sb);
		
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = BaseDao.getConn();
			pst = conn.prepareStatement(sb.toString());
			pst.setObject(1, id);
			
			num = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll(null, pst, conn);
		}
		
		return num;
	} 
}
