
public class ErrObj {
	private String name;
	private String message;
	private boolean isErr;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isErr() {
		return isErr;
	}
	public void setErr(boolean isErr) {
		this.isErr = isErr;
	}
	public ErrObj(String name, String message, boolean isErr) {
		super();
		this.name = name;
		this.message = message;
		this.isErr = isErr;
	}

}
