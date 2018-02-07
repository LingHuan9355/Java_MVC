package com.cshr.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BaseDao {

	private static Properties ps = new Properties();
	
	static {
		InputStream is = BaseDao.class.getResourceAsStream("/com/cshr/conf/jdbc.properties");
	    try {
			ps.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//连接数据库
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
	public static void closeAll(ResultSet rs,Statement st, Connection conn){
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
	
}
