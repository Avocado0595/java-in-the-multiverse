package org.example;

import org.example.controller.AppController;
import org.example.controller.CourseController;
import org.example.controller.StudentController;
import org.example.view.Course.CourseView;
import org.example.view.MainView;
import org.example.view.Student.StudentView;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("App is running...");
        AppController.run();
    }
}