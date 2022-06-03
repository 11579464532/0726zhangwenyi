<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 5/15/2021
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.zhangwenyi.lab2.loginBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Han Ming -- 2020211001000719</h1>
<jsp:useBean id="bean" class="com.zhangwenyi.lab2.loginBean" />
<jsp:setProperty name="bean" property="username" value='<%=session.getAttribute("username")%>'/>
<jsp:setProperty name="bean" property="password" value='<%=session.getAttribute("password")%>'/>
<h2>Welcome,<jsp:getProperty name="bean" property="username"/>
    <%--todo 9 : use jsp:useBean to access the same instance of login bean from request scope--%>
    
    <%--todo 10 : use jsp:getProperty to display username --%>
    
</h2>
</body>
</html>
