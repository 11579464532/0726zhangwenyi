<%@ page import="com.zhangwenyi.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 4/5/2021
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@include file="header.jsp"%>
<h1> User Info</h1>
<%
//    Cookie[] cookies = request.getCookies();
//    for (Cookie c : cookies){
//        out.println(c.getName());
//    }
    User user = (User) session.getAttribute("user");
%>
<table>
    <tr>
        <td>Username:</td><td><%=user.getUsername()%></td>
    </tr><tr>
        <td>Password:</td><td><%=user.getPassword()%></td>
</tr><tr>
        <td>Email:</td><td><%=user.getEmail()%></td>
</tr><tr>
    <td>Gender:</td><td><%=user.getGender()%></td>
</tr><tr>
    <td>Birth Date:</td><td><%=user.getBirthdate()%></td>
</tr>
<tr>
    <a href="updateUser">Update User</a>
</tr>
</table>

<%@include file="footer.jsp"%>
