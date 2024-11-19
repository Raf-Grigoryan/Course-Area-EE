package org.example.courseareaee.service;

import org.example.courseareaee.db.DBConnectionProvider;
import org.example.courseareaee.model.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonService {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserService userService = new UserService();


    public void addLesson(Lesson lesson) {
        try {
            String sql = "INSERT INTO lesson(name,lecturer_name,price,duration,user_id) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, lesson.getLecturerName());
            preparedStatement.setDouble(3, lesson.getPrice());
            preparedStatement.setInt(4, lesson.getDuration());
            preparedStatement.setInt(5, lesson.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lesson";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setPrice(resultSet.getDouble("price"));
                lesson.setDuration(resultSet.getInt("duration"));
                lesson.setUser(userService.getUserById(resultSet.getInt("user_id")));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public List<Lesson> getLessonsByUserId(int userId) {
        List<Lesson> lessons = new ArrayList<>();
        try {
            String sql = "SELECT * FROM lesson WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setLecturerName(resultSet.getString("lecturer_name"));
                lesson.setPrice(resultSet.getDouble("price"));
                lesson.setDuration(resultSet.getInt("duration"));
                lesson.setUser(userService.getUserById(resultSet.getInt("user_id")));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public Lesson getLessonByName(String name) {
        Lesson lesson = null;
        try {
            String sql = "SELECT * FROM lesson WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lesson = Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .duration(resultSet.getInt("duration"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build();
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    public Lesson getLessonById(int id) {
        Lesson lesson = null;
        try {
            String sql = "SELECT * FROM lesson WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lesson = Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lecturerName(resultSet.getString("lecturer_name"))
                        .price(resultSet.getDouble("price"))
                        .duration(resultSet.getInt("duration"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .build();
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    public void deleteLessonById(int id) {
        try{
            String sql = "DELETE FROM lesson WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
