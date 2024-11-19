<%@ page import="java.util.List" %>
<%@ page import="org.example.courseareaee.model.Lesson" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login">
    <% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
        String msg = (String) session.getAttribute("studentAddMessage");
        if (msg != null) {
    %>
    <h1 style="color: red"><%=msg%>
    </h1>
    <%
       session.removeAttribute("studentAddMessage");
    } else {%>
    <h1>REGISTER</h1>
    <%}%>
    <form method="post" action="/addStudent">
        <input type="text" name="name" placeholder="Name" required="required"/>
        <input type="text" name="surname" placeholder="Surname" required="required"/>
        <input type="text" name="email" placeholder="Email" required="required"/>
        <input type="number" name="age" placeholder="Age" required="required"/>
        <select name="lesson_id">
            <%for (Lesson lesson : lessons) {%>
            <option value="<%=lesson.getId()%>"><%=lesson.getName() + " " + lesson.getLecturerName()%>
            </option>
            <%}%>
        </select>
        <button type="submit" class="btn btn-primary btn-block btn-large">ADD STUDENT</button>
    </form>
</div>
</body>
</html>