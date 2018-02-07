<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'showUser.jsp' starting page</title>
    
  </head>
  
  <body>
    <div align="center">
       <h1><a href="index.jsp">首页</a></h1>
       <table border="1" bordercolor="blue" cellspacing="0" width="50%">
          <tr>
             <th>编号</th>
             <th>姓名</th>
             <th>密码</th>
             <th>出生日期</th>
             <th>操作</th>
          </tr>
        <c:forEach items="${slist}" var="user">
	          <tr>
	            <td>${user.id }</td>
	            <td>${user.name }</td>
	            <td>${user.pwd }</td>
	            <td>${user.brithday }</td>
	            <td>
	              <a href="selectOneUser.action?updateId=${user.id}">修改</a>
	              <a href="javascript:Delete(${user.id})">删除</a>
	            </td>
	          </tr>
         </c:forEach>
       </table>
    </div>
  <script type="text/javascript">
       function Delete(did){
           if(confirm("确定删除吗？")){
              location.href="deleteUser.action?deleteId="+did;
          }
       }
        
       </script>
  </body>
</html>
