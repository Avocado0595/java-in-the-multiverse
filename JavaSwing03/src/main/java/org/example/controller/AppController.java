package org.example.controller;

import org.example.view.Course.CourseView;
import org.example.view.MainView;
import org.example.view.Student.StudentView;

public class AppController {
    public static void run(){
        StudentView studentView = new StudentView();
        CourseView courseView = new CourseView();
        MainView f = new MainView(studentView, courseView);

        new StudentController(f.getPnstudent(),f);
        new CourseController(f.getPncourse());
        f.setVisible(true);
    }
}
