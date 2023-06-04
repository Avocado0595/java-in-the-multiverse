package org.example.controller;

import org.example.model.DAO.concrete.MySQLEnrollments;
import org.example.model.DAO.concrete.MySQLStudent;
import org.example.model.DAO.interfaces.StudentDAO;
import org.example.model.Student;
import org.example.view.MainView;
import org.example.view.Student.StudentView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StudentController {
    private final StudentDAO studentDAO;
    private final StudentView studentView;
    private final MainView mainView;
    private final MySQLEnrollments mySQLEnrollments;
    public StudentController(StudentView studentView, MainView mainView){
        this.studentDAO = new MySQLStudent();
        this.mySQLEnrollments = new MySQLEnrollments();
        this.studentView = studentView;
        this.mainView = mainView;
        showStudentList();
        initAction();


    }
    private void showStudentList(){
    try {
        studentView.showStudentList(studentDAO.getAll());
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    public void initAction(){
        studentView.addSelectRowListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                studentView.fillSelectTable();
                studentView.setEnableCourseBtn(false);
            }
        });

        studentView.addClickNew(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentView.enableTextfield(true);
                studentView.resetTextfield();
            }
        });
        studentView.addClickAdd(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = studentView.getStudentInfo();
                try {
                    studentDAO.insert(student);
                    showStudentList();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        studentView.addClickDelete(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentView.isCreatingNew())
                    return;
                Student student = studentView.getStudentInfo();
                try {
                    int op = studentView.showConfirm("Delete student id = " +student.getId()+", name = "+ student.getName());
                    if(op== JOptionPane.YES_OPTION){
                        studentDAO.delete(student);
                        showStudentList();
                        studentView.resetTextfield();
                    }
                    else return;

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        studentView.addClickUpdate(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentView.isCreatingNew())
                    return;
                studentView.enableTextfield(true);

                studentView.setEnableCourseBtn(true);
            }
        });
        studentView.addClickSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = studentView.getStudentInfo();
                try {
                    studentDAO.update(student);
                    showStudentList();
                    studentView.enableTextfield(false);
                    studentView.setEnableCourseBtn(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        studentView.addClickAddCourse(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentView.showCourseList(mainView);
            }
        });
        studentView.addClickDeleteCourse(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int courseId = studentView.getSelectedCourseRow();
                if(courseId!=-1){
                    try {
                        int op = studentView.showConfirm("Delete registered courseid = " +courseId);
                        if(op== JOptionPane.YES_OPTION) {
                            mySQLEnrollments.deleteByStudentAndCourseId(studentView.getStudentInfo().getId(), courseId);
                            studentView.fillSelectTable();
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        studentView.addClickAddACourse(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mySQLEnrollments.insert(studentView.getSelectedCourseId());
                    studentView.setEnableCourseBtn(false);
                    studentView.fillSelectTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
