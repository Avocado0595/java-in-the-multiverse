import java.util.ArrayList;
import java.util.List;

public class UserMockup {
	
	public static List<User> users = new ArrayList<User>() {{
		add(new User("admin","admin","tran van admin", 20));}
	};
	public static boolean findUser(String name, String password) {
		for(User u:users) {
			if(u.getName().equals(name)&&u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	public static User findUserByName(String name) {
		for(User u:users) {
			if(u.getName().equals(name)) {
				return u;
			}
		}
		return null;
	}
	public static void addUser(User user) {
		users.add(user);
	}
}
