package org.example;

import org.example.controller.StudentController;
import org.example.view.Course.CourseView;
import org.example.view.MainView;
import org.example.view.Student.StudentView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        //new MySQL().openConnection();
       //new MySQLStudent().insert(new Student(0,"tx","abc@emmail.com","0123456789", LocalDate.of(2023,5,15)));
//        System.out.println(new MySQLStudent().delete(new Student(2,"tx","abc@emmail.com","0123456789", LocalDate.of(2023,5,15))));
//        List<Student> s = new MySQLStudent().getAll();
//        for(Student i:s){
//            System.out.println(i.getId());
//        }
//        Student s = new MySQLStudent().findById(6);
//        System.out.println(s.getId());
        //new MySQLStudent().update(new Student(3,"thanhxuan","abc@emmail.com","0123456789", LocalDate.of(2023,5,15)));
        StudentView studentView = new StudentView();
        CourseView courseView = new CourseView();
        new MainView(studentView, courseView).setVisible(true);
        StudentController studentController = new StudentController(studentView);
    }
}