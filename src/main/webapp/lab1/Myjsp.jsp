<%--
  Created by IntelliJ IDEA.
  User: hmk05
  Date: 2022/5/20
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UsingServlets</title>
</head>
<body>
<h1>2020211001000719 --- Han Ming</h1><br><br>
<h2>I am MyJsp.jsp</h2><br><br>
<h2>Submit to MyDearServletURL.java</h2>
<form method="post" action="<%=request.getContextPath()+"/MyDearServletURL"%>">
    Name: <input type="text" name="name" placeholder="name"><br>
    Class: <input type="text" name="class" placeholder="class"><br>
    Id: <input type="text" name="id" placeholder="id"><br><br>
    <button type="submit">Send data to server</button>
</form><br><br>
<h2>Submit to MyDearJsp.jsp</h2>
<form method="post" action="MyDearJsp.jsp">
    Name: <input type="text" name="name" placeholder="name"><br>
    Class: <input type="text" name="classNum" placeholder="class"><br>
    Id: <input type="text" name="id" placeholder="id"><br><br>
    <button type="submit">Send data to Jsp</button>
</form>
</body>
</html>
