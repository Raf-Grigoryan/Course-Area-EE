package org.example.courseareaee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private Lesson lesson;
    private User user;
}
