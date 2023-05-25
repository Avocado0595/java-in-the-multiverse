package org.example.model.DAO.interfaces;

import org.example.model.Enrollments;

import java.sql.SQLException;
import java.util.List;

public interface EnrollmentsDAO {
    Enrollments insert(Enrollments en) throws SQLException;
    List<Enrollments> getAll() throws SQLException;
    int delete(Enrollments en) throws SQLException;
    int deleteByStudentAndCourseId(int studentId, int courseId) throws SQLException;
    Enrollments findByStudentAndCourseId(int studentId, int courseId) throws SQLException;
    Enrollments findById(int id) throws SQLException;
    List<Enrollments> findByStudentId(int studentId) throws SQLException;
    List<Enrollments> findByCourseId(int courseId) throws SQLException;
    int update(Enrollments en) throws SQLException;
}
