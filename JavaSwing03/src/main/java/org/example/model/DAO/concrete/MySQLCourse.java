package org.example.model.DAO.concrete;

import org.example.model.DAO.daoFactory.DaoFactory;
import org.example.model.DAO.interfaces.CourseDAO;
import org.example.model.Course;
import org.example.model.RegistedCourse;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySQLCourse implements CourseDAO {
    private static final String
            INSERT = "INSERT INTO course (name, description) VALUES (?, ?)";

    private static final String
            GETALL = "SELECT * FROM course";

    private static final String
            FIND_BY_ID = "SELECT * FROM course WHERE id = ?";

    private static final String
            DELETE = "DELETE FROM course where id = ?";
    private static final String
            UPDATE = "UPDATE course SET name=?, description=? WHERE id=?";
    private static final String
            FIND_BY_STUDENT_ID = "SELECT course.*, enrollments.enrollment_date as enrollment_date FROM course INNER JOIN enrollments ON course.id = enrollments.course_id WHERE enrollments.student_id=?";
    private static final String
            FIND_NOT_REGIS_COURSE = "select * from course where id not in (select course_id from enrollments where student_id = ?);";
    private static final String
            DELETE_ENROLMENTS = "DELETE FROM enrollments where course_id=?";
    @Override
    public Course insert(Course course) throws SQLException {
        if(course == null ||course.getId()!=-1)
            return null;
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, course.getName());
        pstmt.setString(2, course.getDescription());
        pstmt.executeUpdate();
        pstmt.close();
        c.close();

        return course;
    }
    @Override
    public List<Course> getAll() throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection con = DaoFactory.getDatabase().openConnection();
        Statement selectStatement = con.createStatement();
        ResultSet result = selectStatement.executeQuery(GETALL);
        while(result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            String description =  result.getString("description");

            courses.add(new Course(id, name, description));
        }
        selectStatement.close();
        result.close();

        return courses;
    }
    @Override
    public int delete(Course course) throws SQLException {
        Connection c = DaoFactory.getDatabase().openConnection();

        PreparedStatement pstmt0 = c.prepareStatement(DELETE_ENROLMENTS, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt0.setInt(1, course.getId());
        pstmt0.executeUpdate();
        pstmt0.close();

        PreparedStatement pstmt = c.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, course.getId());

        int result = pstmt.executeUpdate();
        pstmt.close();
        c.close();

        return result;
    }
    @Override
    public Course findById(int id) throws SQLException {
        Course course = null;
        Connection con = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = con.prepareStatement(FIND_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            int sid = result.getInt("id");
            String name = result.getString("name");
            String description =  result.getString("description");

            course = new Course(sid, name, description);
        }
        pstmt.close();
        result.close();

        return course;
    }
    @Override
    public int update(Course course) throws SQLException{
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, course.getName());
        pstmt.setString(2, course.getDescription());
        pstmt.setInt(3, course.getId());

        int result = pstmt.executeUpdate();
        pstmt.close();
        c.close();
        return result;
    }

    public List<RegistedCourse> findRegistedByStudentId(int studentId) throws SQLException {
        List<RegistedCourse> courseList = new ArrayList<>();
        Connection con = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = con.prepareStatement(FIND_BY_STUDENT_ID, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, studentId);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            int sid = result.getInt("id");
            String name = result.getString("name");
            String description =  result.getString("description");
            LocalDate enrollmentDate = result.getDate("enrollment_date").toLocalDate();
            courseList.add(new RegistedCourse(sid, name, description, enrollmentDate));
        }
        pstmt.close();
        result.close();

        return courseList;
    }
    public List<Course> findByStudentId(int studentId) throws SQLException {
        List<Course> courseList = new ArrayList<>();
        Connection con = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = con.prepareStatement(FIND_BY_STUDENT_ID, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, studentId);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            int sid = result.getInt("id");
            String name = result.getString("name");
            String description =  result.getString("description");
            courseList.add(new Course(sid, name, description));
        }
        pstmt.close();
        result.close();

        return courseList;
    }
    public List<Course> findNotRegisCourse(int studentId) throws SQLException {
        List<Course> courseList = new ArrayList<>();
        Connection con = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = con.prepareStatement(FIND_NOT_REGIS_COURSE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, studentId);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            int sid = result.getInt("id");
            String name = result.getString("name");
            String description =  result.getString("description");

            courseList.add(new Course(sid, name, description));
        }
        pstmt.close();
        result.close();

        return courseList;
    }
}
