<%@ page import="com.zhangwenyi.lab2.loginBean" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 5/15/2021
  Time: 11:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>validate</title>
</head>
<body>
<%--Todo 1: Use <jsp:useBean> to create a Login bean instance in request scope --%>
<jsp:useBean id="bean" class="com.zhangwenyi.lab2.loginBean" />

<%--Todo 2: Use <jsp:setProperty> to set  beans' property username and password--%>
<jsp:setProperty name="bean" property="username" value='<%=request.getParameter("username")%>'/>
<jsp:setProperty name="bean" property="password" value='<%=request.getParameter("password")%>'/>

<%
   //todo 3: use if check username is admin and ppassword is admin
    String msg = null;
    if (bean.getUsername().equals("admin") && bean.getPassword().equals("admin")){
        //取得产品标识
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username!=null && password!=null){
            session.setAttribute("username", username);
            session.setAttribute("password", password);
        }
%>
<jsp:forward page="welcome.jsp">
    <jsp:param name="username" value='<%=request.getParameter("username")%>'/>
    <jsp:param name="password" value='<%=request.getParameter("password")%>'/>
</jsp:forward>
<%
    } else {
        if (!bean.getUsername().equals("admin")){
            msg = "Username Error";
        }
        if (!bean.getPassword().equals("admin")){
            msg = "Password Error";
        }
%>
<h2>Sorry <%=msg%></h2>
<%@include file="login.jsp"%>
<%
    }
%>
    <%--todo 4: use jsp:forward to welcome.jsp page--%>

    <%--todo 5: else part{ --%>

<%
// todo 6: print username or password error message

%>
    <%--todo 7: use jsp:include login.jsp page --%>

    <%--todo 8: close else --%>

</body>
</html>