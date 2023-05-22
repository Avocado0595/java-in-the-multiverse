package org.example.controller;

import org.example.model.DAO.concrete.MySQLStudent;
import org.example.model.DAO.interfaces.StudentDAO;
import org.example.model.Student;
import org.example.view.Student.StudentView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StudentController {
    private StudentDAO studentDAO;
    private StudentView studentView;
    public StudentController(StudentView studentView){
        this.studentDAO = new MySQLStudent();
        this.studentView = studentView;
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
                studentView.setEnableSaveBtn(true);
            }
        });
        studentView.addClickSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student student = studentView.getStudentInfo();
                try {
                    studentDAO.update(student);
                    showStudentList();
                    studentView.setEnableSaveBtn(false);
                    studentView.enableTextfield(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
