<%--
  Created by IntelliJ IDEA.
  User: hmk05
  Date: 2022/4/29
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demo - 1 - week 10</title>
</head>
<body>
    <h2>Use pageContext object to set, get, remove attribute scope -page,</h2>
    <!-- set attribute -->
<%
    // set attribute - using implicit objects -way1
    pageContext.setAttribute("attName", "in page");
    request.setAttribute("attName", "in request");
    session.setAttribute("attName", "in session");
    application.setAttribute("attName", "in application");

    // set attribute - using  pageContext object - way2
    pageContext.setAttribute("attName", "in page", PageContext.PAGE_SCOPE); // 1
    pageContext.setAttribute("attName", "in request", PageContext.REQUEST_SCOPE); // 2
    pageContext.setAttribute("attName", "in session", PageContext.SESSION_SCOPE); // 3
    pageContext.setAttribute("attName", "in application", PageContext.APPLICATION_SCOPE); // 4
%>

<!-- get attribute -->
    <h1>Get attribute -using implicit objects way1</h1>

    Att value: <%=pageContext.getAttribute("attName")%><br>
    Att value: <%=request.getAttribute("attName")%><br>
    Att value: <%=session.getAttribute("attName")%><br>
    Att value: <%=application.getAttribute("attName")%><br>

    <h1>Get attribute -using pageContext objects way2</h1>
    Att value: <%=pageContext.getAttribute("attName", PageContext.PAGE_SCOPE)%><br>
    Att value: <%=pageContext.getAttribute("attName", PageContext.REQUEST_SCOPE)%><br>
    Att value: <%=pageContext.getAttribute("attName", PageContext.SESSION_SCOPE)%><br>
    Att value: <%=pageContext.getAttribute("attName", PageContext.APPLICATION_SCOPE)%><br>

    <h1>pageContext find attribute - search att in page-->request-->session-->application-->null(if don't have att)</h1>
    Att value: <%=pageContext.findAttribute("attName")%> <!-- which one? -->
</body>
</html>
