<%--
  Created by IntelliJ IDEA.
  User: hmk05
  Date: 2022/5/6
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>demo - 1 - week 11</title>
</head>
<body>
<%
    // set attribute in 4 scopes
    pageContext.setAttribute("attName", "in page");
    request.setAttribute("attName", "in request");
    session.setAttribute("attName", "in session");
    application.setAttribute("attName", "in application");
%>
<h2>find attribute - using java code</h2>
att value : <%=pageContext.findAttribute("attName")%>
<h2>find attribute - using EL code</h2>
att value : ${attName}
<h2>get (not find) attribute from any one scope</h2>
get att from session : ${sessionScope.attName}<br> <!-- same as session.getAttribute("attName") -->
get att from application : ${applicationScope.attName}<br> <!-- same as application.getAttribute("attName") -->
get att from page : ${pageScope.attName}<br> <!-- same as pageContext.getAttribute("attName") -->
get att from request : ${requestScope.attName}<br> <!-- same as request.getAttribute("attName") -->
</body>
</html>
