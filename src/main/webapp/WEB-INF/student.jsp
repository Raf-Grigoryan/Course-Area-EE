<%@ page import="java.util.List" %>
<%@ page import="org.example.courseareaee.model.Student" %>
<%@ page import="org.example.courseareaee.model.User" %><%--
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
<h1><span class="blue">&lt;<span class="yellow">STUDENTS</span></h1>
<a href="/home" target="_blank"><h2 class="blue">>HOME PAGE></a></h2></a>
<a href="/addStudent" target="_blank"><h2 class="blue">>ADD STUDENT></a></h2></a>
<%List<Student> students = (List<Student>) request.getAttribute("students");
    User user = (User) session.getAttribute("user");
%>
<table class="container">
    <thead>
    <tr>
        <th><h1>id</h1></th>
        <th><h1>Name</h1></th>
        <th><h1>Surname</h1></th>
        <th><h1>Email</h1></th>
        <th><h1>age</h1></th>
        <th><h1>Lesson Name</h1></th>
        <th><h1>Lecturer Name</h1></th>
        <th><h1>Duration</h1></th>
        <th><h1>Delete</h1></th>

    </tr>
    </thead>
    <tbody>
    <%for (Student student : students) {%>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSurname()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <td><%=student.getLesson().getName()%>
        </td>
        <td><%=student.getLesson().getLecturerName()%>
        </td>
        <td><%=student.getLesson().getDuration()%>
        </td>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">REMOVE</a>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>
