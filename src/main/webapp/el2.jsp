<%--
  Created by IntelliJ IDEA.
  User:  zhangwenyi
  Date: 2022/5/13
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo 4 - week 11</title>
</head>
<body>
<h2>Get request parameters - using El code</h2>
name : ${param.name}<br> <!-- param can get request param -->
Id : ${param.id}<br>
Subject : ${paramValues.subject[0]}, ${paramValues.subject[1]}, ${paramValues.subject[2]}<br>
<hr>
<h2>Get request header using El code</h2>
Host : ${header.host}<br>
Accept : ${header.Accept}<br>
<hr>
<h2>Get cookie using El code</h2>
JSESSIONID value : ${cookie.JSESSIONID.value}<br>
<hr>
<h2>Get context init param using El code</h2>
Driver : ${initParam.driver}<br>
Url : ${initParam.url}<br>
Password : ${initParam.password}<br>
<hr>
<h2>Use pageContext object in El</h2>
Session id : ${pageContext.session.id}<br>
request method : ${pageContext.request.method}<br>
<hr>
<h2>Use empty operator in El</h2>
${empty str}<br>
${empty personList}<br>
</body>
</html>
