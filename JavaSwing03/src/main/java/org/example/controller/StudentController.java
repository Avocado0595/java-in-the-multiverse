package org.example.controller;

import org.example.Main;
import org.example.model.DAO.concrete.MySQLEnrollments;
import org.example.model.DAO.concrete.MySQLStudent;
import org.example.model.DAO.interfaces.StudentDAO;
import org.example.model.Enrollments;
import org.example.model.Student;
import org.example.view.MainView;
import org.example.view.Student.StudentView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

public class StudentController {
    private StudentDAO studentDAO;
    private StudentView studentView;
    private MainView mainView;
    private MySQLEnrollments mySQLEnrollments;
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
                    studentDAO.delete(student);
                    showStudentList();
                    studentView.resetTextfield();
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
                studentView.showCourseList(mainView, false);
            }
        });
        studentView.addClickDeleteCourse(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentView.showCourseList(mainView, true);
            }
        });
        studentView.addClickDeleteACourse(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mySQLEnrollments.deleteByStudentAndCourseId(studentView.getSelectedCourseId().getStudentId(),studentView.getSelectedCourseId().getCourseId());
                    studentView.fillSelectTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
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
