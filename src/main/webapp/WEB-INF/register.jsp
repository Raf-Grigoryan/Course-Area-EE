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
        session.invalidate();
    } else {%>
    <h1>REGISTER</h1>
    <%}%>
    <form method="post" action="/register">
        <input type="text" name="name" placeholder="Name" required="required"/>
        <input type="text" name="surname" placeholder="Surname" required="required"/>
        <input type="text" name="email" placeholder="Email" required="required"/>
        <input type="password" name="password" placeholder="Password" required="required"/>
        <select name="user_type">
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
        </select>
        <button type="submit" class="btn btn-primary btn-block btn-large">LOGIN</button>
    </form>
</div>
</body>
</html>