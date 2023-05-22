package org.example.model.DAO.interfaces;

import org.example.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    Student insert(Student student) throws SQLException;
    List<Student> getAll() throws SQLException;
    int delete(Student student) throws SQLException;
    Student findById(int id) throws SQLException;
    int update(Student student) throws SQLException;
}
