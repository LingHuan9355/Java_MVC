<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML >
<html>
  <head>
    <title>My JSP 'addUser.jsp' starting page</title>

  </head>
  
  <body>
      <div align="center">
	    <form action="addUser.action" method="post">
	    <!-- name属性值需要与ActionForm中的属性名要一致 -->
	                     姓名: <input type="text" name="name"/><br/>
	                     密码: <input type="password" name="pwd"/><br/>
	                     日期: <input type="date" name="brithday"/><br/>
	        <input type="submit" value=" 添加 "/>
	     </form>
       </div>
  </body>
</html>
