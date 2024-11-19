package org.example.courseareaee.servlet;

import org.example.courseareaee.model.Lesson;
import org.example.courseareaee.model.Student;
import org.example.courseareaee.model.User;
import org.example.courseareaee.model.UserType;
import org.example.courseareaee.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private final StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Student> students;
        if (user.getUserType() == UserType.ADMIN) {
            students = studentService.allStudents();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/WEB-INF/student.jsp").forward(req, resp);
        } else {
            students = studentService.getStudentsByUserId(user.getId());
            req.setAttribute("students", students);
            req.getRequestDispatcher("/WEB-INF/student.jsp").forward(req, resp);
        }
    }
}
