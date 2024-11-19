<%@ page import="java.util.List" %>
<%@ page import="org.example.courseareaee.model.Lesson" %>
<%@ page import="org.example.courseareaee.model.User" %>
<%@ page import="org.example.courseareaee.model.UserType" %><%--
  Created by IntelliJ IDEA.
  User: raf
  Date: 19.11.24
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="/css/table.css">
</head>
<body>
<h1><span class="blue">&lt;<span class="yellow">LESSON</span></h1>
<a href="/home" target="_blank"><h2 class="blue">HOME PAGE</a></h2></a>
<a href="/addLesson" target="_blank"><h2 class="blue">ADD LESSON</a></h2></a>
<%
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
    User user = (User) session.getAttribute("user");
%>
<table class="container">
    <thead>
    <tr>
        <th><h1>id</h1></th>
        <th><h1>Name</h1></th>
        <th><h1>Lecturer Name</h1></th>
        <th><h1>Price</h1></th>
        <th><h1>Duration</h1></th>
        <%if (user.getUserType() == UserType.ADMIN) {%>
        <th><h1>DELETE</h1></th>
        <% }%>

    </tr>
    </thead>
    <tbody>
    <%for (Lesson lesson : lessons) {%>
    <tr>
        <td><%=lesson.getId()%>
        </td>
        <td><%=lesson.getName()%>
        </td>
        <td><%=lesson.getLecturerName()%>
        </td>
        <td><%=lesson.getPrice()%>
        </td>
        <td><%=lesson.getDuration()%>
        </td>
        <%if (user.getUserType() == UserType.ADMIN) {%>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>">REMOVE</a>
        </td>
        <%}%>

    </tr>

    <%}%>
    </tbody>
</table>
</body>
</html>
