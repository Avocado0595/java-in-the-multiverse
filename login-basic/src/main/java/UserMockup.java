import java.util.ArrayList;
import java.util.List;

public class UserMockup {
	
	public static List<User> users = new ArrayList<User>() {{
		add(new User("admin","admin"));}
	};
	public static boolean findUser(User user) {
		for(User u:users) {
			if(u.getName().equals(user.getName())&&u.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}
	public static boolean findUserByName(String name) {
		for(User u:users) {
			if(u.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	public static void addUser(User user) {
		users.add(user);
	}
}
