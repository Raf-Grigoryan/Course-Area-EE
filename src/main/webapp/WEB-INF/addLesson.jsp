<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login">
    <%
        String msg = (String) session.getAttribute("msg");
        if (msg != null) {
    %>
    <h1 style="color: red"><%=msg%>
    </h1>
    <%
        session.removeAttribute("msg");
    } else {%>
    <h1>ADD LESSON</h1>
    <%}%>
    <form method="post" action="/addLesson">
        <input type="text" name="name" placeholder="Name" required="required"/>
        <input type="text" name="lecturer_name" placeholder="Lecturer Name" required="required"/>
        <input type="number" name="price" placeholder="Price" required="required"/>
        <input type="number" name="duration" placeholder="Duration" required="required"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">ADD LESSON</button>
    </form>
</div>
</body>
</html>