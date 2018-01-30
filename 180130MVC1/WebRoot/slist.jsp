<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'slist.jsp' starting page</title>

  </head>
  
  <body>
       
       <table border="1" bordercolor="blue" width="50%" align="center" cellspacing="0">
          <tr>
    			<td>编号</td>
    			<td>姓名</td>
    			<td>金额</td>
    			<td>生日</td>
    	  </tr>
          <c:forEach items="${slist}" var="stu">
          <tr>
    			<td>${stu.id}</td>
    			<td>${stu.name}</td>
    			<td>${stu.momey}</td>
    			<td>${stu.brithday}</td>
    	  </tr>
          </c:forEach>
       </table>


  </body>
</html>
