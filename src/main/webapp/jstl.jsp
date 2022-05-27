<%--
  Created by IntelliJ IDEA.

  Date: 2022/5/13
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo - 1 - week 12</title>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Use c:forEach to repeat fixed number of times</h2>
<ul>
    <c:forEach var="i" begin="1" end="10">
        <li>${i}</li>
    </c:forEach>
</ul>
<hr>
<h2>Use c:forEach to repeat fixed number of times - use step</h2>
<ul>
    <c:forEach var="i" begin="1" end="10" step="2">
        <li>${i}</li> <!-- 1 3 5 7 9 -->
    </c:forEach>
</ul>
<hr>
<h2>Use c:foreach to get items from array</h2>
<%
    String[] words = {"biz", "foo", "boo"};
    pageContext.setAttribute("words", words);
%>
<ul>
    <c:forEach var="w" items="${words}">
        <li>${w}</li>
    </c:forEach>
</ul>

</body>
</html>
