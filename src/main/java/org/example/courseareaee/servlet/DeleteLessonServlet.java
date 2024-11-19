package org.example.courseareaee.servlet;

import org.example.courseareaee.service.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteLesson")
public class DeleteLessonServlet extends HttpServlet {
    private final LessonService lessonService = new LessonService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lessonId = Integer.parseInt(req.getParameter("id"));
        lessonService.deleteLessonById(lessonId);
        req.getRequestDispatcher("/lesson").forward(req, resp);
    }
}
