package org.example.courseareaee.servlet;

import org.example.courseareaee.model.User;
import org.example.courseareaee.model.UserType;
import org.example.courseareaee.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");
            UserType userType = UserType.valueOf(req.getParameter("userType"));
            user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .userType(userType)
                    .build();
            userService.addUser(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/home");
        } else {
        req.getSession().setAttribute("msg", "The email exists, please change the email address");
        resp.sendRedirect("/register");
        }
    }
}
