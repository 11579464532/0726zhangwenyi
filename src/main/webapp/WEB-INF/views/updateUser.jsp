<%@ page import="com.zhangwenyi.model.User" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: hmk05
  Date: 2022/4/17
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<h1> Update User Info</h1>
<%
    String username = null, password = null, email = null, gender = "";
    Date birthDate = null;
    User user = (User) session.getAttribute("user");
    if (user != null) {
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        gender = user.getGender();
        birthDate = user.getBirthdate();
    }
%>
<form method="post" action="updateUser"><!--action to link jsp  to servlet -->
    Username <input type="text" name="username" value="<%=username%>"/><br/>
    password <input type="password" name="password" value="<%=password%>"/><br/>
    Email <input type="text" name="email" value="<%=email%>"/><br/>

    Gender <%if (gender.equals("male")){%><input type="radio" name="gender" value="male">male
    <%}else {%><input type="radio" name="gender" value="female">Female <%}%><br/>
    Birth Date <input type="text" name="birthDate" value="<%=birthDate%>"/>(yyyy-mm-dd)<br/>
    <input type="submit" value="UpdateUser"/>

</form>
<%@include file="footer.jsp"%>
