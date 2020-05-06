package Testers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Objects.Login;

class LoginTest {
	
	private Login l1;

	@Test
	void testLogin() {
		l1 = new Login(1, "ginaK", "ginaK@gmail.com", "password123", "user");
		
		assertEquals(l1.toString(), "Login [userId=1, userName=ginaK, email=ginaK@gmail.com,"
				+ " password=password123, userType=user]");
	}
	
	@Test
	void testPrepInsertQuery() {
		l1 = new Login(1, "ginaK", "ginaK@gmail.com", "password123", "user");
		assertEquals(l1.prepInsertQuery(), "INSERT INTO Login (userName, email, password, "
				+ "userType) VALUES('ginaK', 'ginaK@gmail.com', 'password123', "
				+ "'user')");
	}

	@Test
	void testToString() {
		l1 = new Login(1, "ginaK", "ginaK@gmail.com", "password123", "user");
		
		assertEquals(l1.toString(), "Login [userId=1, userName=ginaK, email=ginaK@gmail.com,"
				+ " password=password123, userType=user]");
	}
}
