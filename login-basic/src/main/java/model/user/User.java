package model.user;

public class User {
	public User(String email, String password, String name, String avatar) {
		this.email = email;
		this.password = password;
		this.name=name;
		this.avatar = avatar;
	}
	public User(String email, String name) {
		this.name = name;
		this.password = "";
		this.email=email;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
	private String avatar;

	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
