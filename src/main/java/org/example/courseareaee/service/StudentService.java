package org.example.courseareaee.service;

import org.example.courseareaee.db.DBConnectionProvider;
import org.example.courseareaee.model.Student;
import org.example.courseareaee.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserService userService = new UserService();
    private final LessonService lessonService = new LessonService();


    public void addStudent(Student student) {
        try {
            String sql = "INSERT INTO student(name,surname,email,age,lesson_id,user_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.setInt(6, student.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> allStudents() {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .lesson(lessonService.getLessonById(resultSet.getInt("lesson_id")))
                        .build();
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getStudentsByUserId(int userId) {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT * FROM student WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .lesson(lessonService.getLessonById(resultSet.getInt("lesson_id")))
                        .build();
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentByEmail(String email) {
        Student student = null;
        try {
            String sql = "SELECT * FROM student WHERE email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .lesson(lessonService.getLessonById(resultSet.getInt("lesson_id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public void deleteStudent(int id) {
        try{
            String sql = "DELETE FROM student WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
