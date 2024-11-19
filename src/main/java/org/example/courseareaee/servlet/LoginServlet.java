package org.example.courseareaee.servlet;

import org.example.courseareaee.model.User;
import org.example.courseareaee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserByEmailAndPassword(req.getParameter("email"), req.getParameter("password"));
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/home");
        } else {
            resp.sendRedirect("/index.jsp");
        }
    }
}
