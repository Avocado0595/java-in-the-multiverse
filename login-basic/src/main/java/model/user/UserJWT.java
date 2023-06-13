package model.user;

public class UserJWT {
	private String email;
	private String name;
	public UserJWT(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
