package Objects;

public class Login {

	private int userId;
	private String userName, email, password, userType;
	
	public Login(int userId, String userName, String email, String password, String userType) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	
	public String prepInsertQuery() {
		return String.format("INSERT INTO Login (userName, email, password, userType) VALUES"
				+ "('%s', '%s', '%s', '%s')", userName, email, password, userType);
	}

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", userType=" + userType + "]";
	}

	
}
