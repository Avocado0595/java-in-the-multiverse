package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import model.MySQLDb;

public class UserMySQL implements UserDAO {

	@Override
	public User insert(User user) throws SQLException {
		final String INSERT = "INSERT INTO user (email, password, name, avatar) VALUES (?, ?, ?, ?)";
		if(user == null)
            return null;
        Connection c = MySQLDb.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, user.getEmail());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getAvatar());
        pstmt.executeUpdate();
        pstmt.close();
        c.close();
        return user;
	}

	@Override
	public List<User> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(User user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findByEmail(String email) throws SQLException {
		final String FIND_BY_EMAIL = "select * from user where email=?";
		if(email == "")
            return null;
        Connection c = MySQLDb.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(FIND_BY_EMAIL, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, email);
        ResultSet users = pstmt.executeQuery();
        User user = null;
        
        while(users.next()) {
        	String name = users.getString("name");
        	String password = users.getString("password");
        	String avatar = users.getString("avatar");
        	user = new User(email, password, name, avatar);
        }
        pstmt.close();
        c.close();
        return user;
	}

	@Override
	public void update(User user) throws SQLException {
		// TODO Auto-generated method stub
		final String UPDATE = "update user set name=? where email=?";
		if(user == null)
            return;
        Connection c = MySQLDb.getDatabase().openConnection();
        PreparedStatement pstmt = c.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getEmail());
        pstmt.executeUpdate();
        pstmt.close();
        c.close();
	}

}
