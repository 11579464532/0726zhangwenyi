<%--
  Created by IntelliJ IDEA.
  User: hmk05
  Date: 2022/5/20
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyDearJsp</title>
</head>
<body>
<%
    String name = request.getParameter("name");
    String classNum = request.getParameter("classNum");
    String id = request.getParameter("id");
%>
<h1>2020211001000719 --- Han Ming</h1><br><br>
<h2>I am MyDearJsp.jsp</h2><br><br>
<h2>Using Java Code</h2>
Name: <%=name%><br>
Class: <%=classNum%><br>
ID: <%=id%><br><br>
<h2>Using EL</h2>
Name: ${param.name}<br>
Class: ${param.classNum}<br>
ID: ${param.id}<br>
</body>
</html>
