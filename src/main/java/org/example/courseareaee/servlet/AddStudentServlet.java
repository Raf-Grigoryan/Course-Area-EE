package org.example.courseareaee.servlet;

import org.example.courseareaee.model.Lesson;
import org.example.courseareaee.model.Student;
import org.example.courseareaee.model.User;
import org.example.courseareaee.service.LessonService;
import org.example.courseareaee.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    private final LessonService lessonService = new LessonService();
    private final StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonService.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String email = req.getParameter("email");
        Student student = studentService.getStudentByEmail(email);
        if (student == null) {
            student = Student.builder()
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(email)
                    .age(Integer.parseInt(req.getParameter("age")))
                    .user(user)
                    .lesson(lessonService.getLessonById(Integer.parseInt(req.getParameter("lesson_id"))))
                    .build();
            studentService.addStudent(student);
            resp.sendRedirect("/student");
        } else {
            req.getSession().setAttribute("studentAddMessage", "Student email already exists");
            resp.sendRedirect("/addStudent");
        }
    }
}
