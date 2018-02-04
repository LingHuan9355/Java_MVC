<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'reg.jsp' starting page</title>
  </head>
  
  <body>
    <div align="center">
       <form action="reg.do" method="post">
       <!-- name属性值需要与ActionForm中的属性名要一致 -->
		       用户名：<input name="name"/><br/>
		       工资：<input name="momey"/><br/>
		       日期：<input  name="brithday"/><br/>
           <input type="submit" value="提交"/>
       </form>
    </div>

  </body>
</html>
