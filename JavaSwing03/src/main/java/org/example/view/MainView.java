package org.example.view;

import org.example.view.Course.CourseView;
import org.example.view.Student.StudentView;

import javax.swing.*;

public class MainView extends JFrame {
    private JTabbedPane jTabbedPane1;
    private JPanel pnstudent;
    private JPanel pncourse;
    public MainView(StudentView pnstudent, CourseView pncourse){
        this.pnstudent = pnstudent;
        this.pncourse = pncourse;
        initComponents();
    }
    private void initComponents(){
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane1.addTab("Student", null, pnstudent, "");
        jTabbedPane1.addTab("Course", null, pncourse, "");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(786,551);
        add(jTabbedPane1);
    }
}
