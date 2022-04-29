<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 4/5/2021
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp"%>
<h1> Login</h1>
<%
//if(!(request.getAttribute("message")==null)){
//    out.println(request.getAttribute("message"));
//}
    Cookie[] allCookies = request.getCookies();
    String cUserName, cPassWord, rememberMeVal = null;
    if(allCookies != null) {
        for (Cookie c: allCookies) {
            if(c.getName().equals("cUserName")){
                cUserName = c.getValue();
            }
            if(c.getName().equals("cPassWord")){
                cPassWord = c.getValue();
            }
            if(c.getName().equals("rememberMeVal")){
                rememberMeVal = c.getValue();
            }
        }
    }
%>
<form method="post" action="login">
    Username : <input type="text" name="username"><br/>
    Password : <input type="password" name="password"><br/>
    <input type="checkbox" name="rememberMe" value="1" <% rememberMeVal.equals("1") ? "checked" : " " %> /> Remember Me <br/>
    <input type="submit" value="Submit"/>
</form>
<%@include file="footer.jsp"%>