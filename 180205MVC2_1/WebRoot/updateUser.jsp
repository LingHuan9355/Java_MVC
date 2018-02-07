<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'updateUser.jsp' starting page</title>
  </head>
  
  <body>
     <div align="center">
         <%--<form action="updateUser.action?uid=${userInfo.id}" method="post">  
         --%>
         <form action="updateUser.action" method="post">
         <input type="hidden" name="id" value="${userInfo.id"/>    
	                     姓名：<input type="text" name="name"  value="${userInfo.name}"/><br/>
	                     密码： <input type="password" name="pwd" value="${userInfo.pwd}"><br/>
	                     日期 :<input type="date" name="brithday" value="${userInfo.brithday}"/><br/>     
         <input type="submit" value=" 修改 "/>
       </form>
     
     </div>
  
  </body>
</html>
