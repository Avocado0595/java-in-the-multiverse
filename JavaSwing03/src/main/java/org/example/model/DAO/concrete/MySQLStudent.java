package org.example.model.DAO.concrete;


import org.example.model.DAO.daoFactory.DaoFactory;
import org.example.model.DAO.interfaces.StudentDAO;
import org.example.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MySQLStudent implements StudentDAO {
    private static final String
            INSERT = "INSERT INTO student (name, email,phone,dob) VALUES (?, ?, ?, ?)";

    private static final String
            GETALL = "SELECT * FROM student";

    private static final String
            FIND_BY_ID = "SELECT * FROM student WHERE id = ?";

    private static final String
            DELETE = "DELETE FROM student where id = ?";
    private static final String
            UPDATE = "UPDATE student SET name=?, email=?, phone=?, dob=? WHERE id=?";
    @Override
    public Student insert(Student student) throws SQLException {
        if(student == null ||student.getId()!=-1)
            return null;
        Connection c = DaoFactory.getDatabase().openConnection();

        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getEmail());
        pstmt.setString(3, student.getPhone());
        pstmt.setString(4, student.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        pstmt.executeUpdate();

        pstmt.close();
        System.out.println(pstmt);
        c.close();

        return student;
    }
    @Override
    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        Connection con = DaoFactory.getDatabase().openConnection();
        Statement selectStatement = con.createStatement();
        ResultSet result = selectStatement.executeQuery(GETALL);
        while(result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            String email =  result.getString("email");
            String phone = result.getString("phone");
            LocalDate dob = result.getDate("dob").toLocalDate();
            students.add(new Student(id, name, email, phone, dob));
        }
        selectStatement.close();
        result.close();

        return students;
    }
    @Override
    public int delete(Student student) throws SQLException {
        if(student==null)
            return -1;
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, student.getId());

        int result = pstmt.executeUpdate();
        pstmt.close();
        c.close();

        return result;
    }
    @Override
    public Student findById(int id) throws SQLException {
        Student s = null;
        Connection con = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = con.prepareStatement(FIND_BY_ID, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            int sid = result.getInt("id");
            String name = result.getString("name");
            String email =  result.getString("email");
            String phone = result.getString("phone");
            LocalDate dob = result.getDate("dob").toLocalDate();
            s = new Student(sid, name, email, phone, dob);
        }
        pstmt.close();
        result.close();

        return s;
    }
    @Override
    public int update(Student student) throws SQLException{
        if(student==null)
            return -1;
        Connection c = DaoFactory.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getEmail());
        pstmt.setString(3, student.getPhone());
        pstmt.setString(4, student.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        pstmt.setInt(5, student.getId());

        int result = pstmt.executeUpdate();
        pstmt.close();
        c.close();
        return result;
    }
}
