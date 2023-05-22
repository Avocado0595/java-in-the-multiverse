package org.example.model.DAO.interfaces;

import org.example.model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    Course insert(Course course) throws SQLException;
    List<Course> getAll() throws SQLException;
    int delete(Course course) throws SQLException;
    Course findById(int id) throws SQLException;
    int update(Course course) throws SQLException;
}
