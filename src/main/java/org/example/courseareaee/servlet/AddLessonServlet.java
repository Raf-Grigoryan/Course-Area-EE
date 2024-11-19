package org.example.courseareaee.servlet;

import org.example.courseareaee.model.Lesson;
import org.example.courseareaee.model.User;
import org.example.courseareaee.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addLesson")
public class AddLessonServlet extends HttpServlet {

    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Lesson lesson = lessonService.getLessonByName(name);
        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user);
        if (lesson == null) {
            lesson = Lesson.builder()
                    .name(name)
                    .lecturerName(req.getParameter("lecturer_name"))
                    .price(Double.parseDouble(req.getParameter("price")))
                    .duration(Integer.parseInt(req.getParameter("duration")))
                    .user(user)
                    .build();
            lessonService.addLesson(lesson);
          resp.sendRedirect("/lesson");
        } else {
            req.getSession().setAttribute("msg", "Lesson already exists");
            resp.sendRedirect("/addLessons");
        }

    }
}
