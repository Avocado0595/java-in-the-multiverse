package org.example.model.DAO.concrete;

import org.example.model.DAO.daoFactory.DaoFactory;
import org.example.model.DAO.interfaces.EnrollmentsDAO;
import org.example.model.Enrollments;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MySQLEnrollments implements EnrollmentsDAO {
    private static final String
            INSERT = "INSERT INTO enrollments (student_id, course_id,enrollment_date) VALUES (?, ?, ?)";

    private static final String
            GETALL = "SELECT * FROM enrollments";

    private static final String
            FIND_BY_ID = "SELECT * FROM enrollments WHERE id = ?";
    private static final String
            FIND_BY_STUDENT_ID = "SELECT * FROM enrollments WHERE enrollments_id = ?";
    private static final String
            FIND_BY_COURSE_ID = "SELECT * FROM enrollments WHERE course_id = ?";

    private static final String
            DELETE = "DELETE FROM enrollments where id = ?";
    private static final String
            DELETE_BY_STUDENT_COURSE = "DELETE FROM enrollments where student_id = ? and course_id=?";
    private static final String
            FIND_BY_STUDENT_COURSE = "select * FROM enrollments where student_id = ? and course_id=?";
    private static final String
            UPDATE = "UPDATE enrollments SET enrollments_id=?, course_id=? WHERE id=?";
    @Override
    public Enrollments insert(Enrollments enrollments) throws SQLException {
        if(findByStudentAndCourseId(enrollments.getStudentId(), enrollments.getCourseId())!=null)
            return null;
        Connection c = DaoFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

        pstmt.setInt(1, enrollments.getStudentId());
        pstmt.setInt(2, enrollments.getCourseId());
        pstmt.setString(3, enrollments.getEnrollmentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        pstmt.executeUpdate();
        pstmt.close();
        c.close();

        return enrollments;
    }
    @Override
    public List<Enrollments> getAll() throws SQLException {
        List<Enrollments> enrollmentss = new ArrayList<>();
        Connection con = DaoFactory.getDatabase().openConnection();
        Statement selectStatement = con.createStatement();
        ResultSet result = selectStatement.executeQuery(GETALL);
        while(result.next()){
            int id = result.getInt("id");
            int studentId = result.getInt("student_id");
            int courseId = result.getInt("course_id");
            LocalDate enrollmentDate = result.getDate("enrollment_date").toLocalDate();
            enrollmentss.add(new Enrollments(id, studentId, courseId, enrollmentDate));
        }
        selectStatement.close();
        result.close();

        return enrollmentss;
    }
    @Override
    public int delete(Enrollments enrollments) throws SQLException {
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, enrollments.getId());

        int result = pstmt.executeUpdate();
        pstmt.close();
        c.close();

        return result;
    }

    @Override
    public int deleteByStudentAndCourseId(int studentId, int courseId) throws SQLException {
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(DELETE_BY_STUDENT_COURSE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, studentId);
        pstmt.setInt(2, courseId);

        int result = pstmt.executeUpdate();
        pstmt.close();
        c.close();

        return result;
    }
    @Override
    public Enrollments findByStudentAndCourseId(int studentId, int courseId) throws SQLException {
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(FIND_BY_STUDENT_COURSE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, studentId);
        pstmt.setInt(2, courseId);

        ResultSet results = pstmt.executeQuery();
        Enrollments result = null;
        while(results.next()){
            int sid = results.getInt("id");
            LocalDate enrollmentDate = results.getDate("enrollment_date").toLocalDate();
            result = new Enrollments(sid, studentId, courseId, enrollmentDate);
        }
        pstmt.close();
        c.close();

        return result;
    }

    @Override
    public Enrollments findById(int id) throws SQLException {
        Enrollments s = null;
        Connection con = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = con.prepareStatement(FIND_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            int sid = result.getInt("id");
            int studentId = result.getInt("student_id");
            int courseId = result.getInt("course_id");
            LocalDate enrollmentDate = result.getDate("enrollment_date").toLocalDate();
            s = new Enrollments(sid, studentId, courseId, enrollmentDate);
        }
        pstmt.close();
        result.close();

        return s;
    }

    @Override
    public List<Enrollments> findByStudentId(int studentId) throws SQLException {
        List<Enrollments> enrolls = new ArrayList<>();
        Connection con = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = con.prepareStatement(FIND_BY_STUDENT_ID, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, studentId);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            int sid = result.getInt("id");
            int courseId = result.getInt("course_id");
            LocalDate enrollmentDate = result.getDate("enrollment_date").toLocalDate();
            enrolls.add(new Enrollments(sid, studentId, courseId, enrollmentDate));
        }
        pstmt.close();
        result.close();

        return enrolls;
    }

    @Override
    public List<Enrollments> findByCourseId(int courseId) throws SQLException {
        List<Enrollments> enrolls = new ArrayList<>();
        Connection con = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = con.prepareStatement(FIND_BY_COURSE_ID, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, courseId);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            int sid = result.getInt("id");
            int studentId = result.getInt("student_id");
            LocalDate enrollmentDate = result.getDate("enrollment_date").toLocalDate();
            enrolls.add(new Enrollments(sid, courseId, courseId, enrollmentDate));
        }
        pstmt.close();
        result.close();

        return enrolls;
    }

    @Override
    public int update(Enrollments enrollments) throws SQLException{
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, enrollments.getStudentId());
        pstmt.setInt(2, enrollments.getCourseId());
        pstmt.setString(3, enrollments.getEnrollmentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        pstmt.setInt(4, enrollments.getId());

        int result = pstmt.executeUpdate();
        pstmt.close();
        c.close();
        return result;
    }
}
