<%@ page import="com.zhangwenyi.week11.Person" %>
<%@ page import="com.zhangwenyi.week11.Dog" %><%--
  Created by IntelliJ IDEA.

  Date: 2022/5/6
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //IN MVC this write in controller

    // create bean objects
    Person person = new Person();
    person.setName("TOM");

    Dog dog = new Dog();
    dog.setName("TOMMY");
    person.setDog(dog);

    //set in any one scope
    request.setAttribute("personAtt", person);
%>
<h2>Person's dog name - using java code</h2>
<%
    Person p = (Person) pageContext.findAttribute("personAtt");
    Dog d = p.getDog();
    out.println("person's dog name --> "+d.getName());
%>
<h2>Person's dog name - using EL code</h2>
Persons' dog name : ${personAtt.dog.name}
</body>
</html>
