package model.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
	User insert(User user) throws SQLException;
    List<User> getAll() throws SQLException;
    int delete(User user) throws SQLException;
    User findByEmail(String email) throws SQLException;
    void update(User user) throws SQLException;
}
