<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="login">
    <h1>Login</h1>
    <form method="post" action="/login">
        <input type="text" name="email" placeholder="Username" required="required"/>
        <input type="password" name="password" placeholder="Password" required="required"/>
        <button type="submit" class="btn btn-primary btn-block btn-large">LOGIN</button>
    </form>
    <a href="/register" style="color: white">Register</a>
</div>
</body>
</html>