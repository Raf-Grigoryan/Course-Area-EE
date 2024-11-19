package org.example.courseareaee.servlet;

import org.example.courseareaee.model.Lesson;
import org.example.courseareaee.model.User;
import org.example.courseareaee.model.UserType;
import org.example.courseareaee.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lesson")
public class LessonServlet extends HttpServlet {
    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Lesson> lessons;
        if (user.getUserType() == UserType.ADMIN) {
            lessons = lessonService.getAllLessons();
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/lesson.jsp").forward(req, resp);
        } else {
            lessons = lessonService.getLessonsByUserId(user.getId());
            req.setAttribute("lessons", lessons);
            req.getRequestDispatcher("/WEB-INF/lesson.jsp").forward(req, resp);
        }
    }
}
