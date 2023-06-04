package org.example.controller;

import org.example.model.DAO.concrete.MySQLCourse;
import org.example.model.DAO.interfaces.CourseDAO;
import org.example.model.Course;
import org.example.view.Course.CourseView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CourseController {

    private final CourseDAO courseDAO;
    private final CourseView courseView;

    public CourseController(CourseView courseView){
        this.courseDAO = new MySQLCourse();
        this.courseView = courseView;
        showCourseList();
        initAction();

    }
    private void showCourseList(){
        try {
            courseView.showCourseList(courseDAO.getAll());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void initAction(){
        courseView.addSelectRowListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                courseView.fillSelectTable();
            }
        });

        courseView.addClickNew(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courseView.enableTextfield(true);
                courseView.resetTextfield();
            }
        });
        courseView.addClickAdd(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course course = courseView.getCourseInfo();
                try {
                    courseDAO.insert(course);
                    showCourseList();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        courseView.addClickDelete(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(courseView.isCreatingNew())
                    return;
                Course course = courseView.getCourseInfo();
                try {
                    int op = courseView.showConfirm("Delete course id = "+course.getId());
                    if(op==JOptionPane.YES_OPTION){
                        courseDAO.delete(course);
                        showCourseList();
                        courseView.resetTextfield();
                    }
                    else return;

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        courseView.addClickUpdate(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(courseView.isCreatingNew())
                    return;
                courseView.enableTextfield(true);
                courseView.setEnableSaveBtn(true);
            }
        });
        courseView.addClickSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Course course = courseView.getCourseInfo();
                try {
                    courseDAO.update(course);
                    showCourseList();
                    courseView.setEnableSaveBtn(false);
                    courseView.enableTextfield(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }
}
