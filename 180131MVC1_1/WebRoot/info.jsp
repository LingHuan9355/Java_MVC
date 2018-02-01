<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'info.jsp' starting page</title>

  </head>
  
  <body>
      <div align="center">
        <form action="info.action" method="post">
		         姓名：<input type="text" name="name"/><br/>
		         性别：<input type="radio" name="sex" value="男">男
		       <input type="radio" name="sex" value="女">女<br/>
		         籍贯：<select name="address">
		         <option value="">--请选择--</option>
		         <option value="北京">北京</option>
		         <option value="上海">上海</option>
		         <option value="广东">广东</option>
		         <option value="重庆">重庆</option>
		         <option value="湖南">湖南</option>
		         <option value="湖北">湖北</option>
		        </select><br/>
		        <input type="submit" value="提交"/>
        </form>
      </div>

  </body>
</html>
