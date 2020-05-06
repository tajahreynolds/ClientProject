package Testers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Objects.PersonalBookShelf;

class PersonalBookShelfTest {
	
	private PersonalBookShelf p1;

	@Test
	void testLogin() {
		p1 = new PersonalBookShelf(1, 2,0);
		
		assertEquals(p1.toString(), "PersonalBookShelf [userId=1, bookId=2, isRead=0]");
		
	}
	
	@Test
	void testPrepInsertQuery() {
		p1 = new PersonalBookShelf(1, 2,0);
		assertEquals(p1.prepInsertQuery(), "INSERT INTO PersonalBookShelf (userId, bookId, isRead) VALUES"
				+ "('1', '2', '0')");
	}
	
	@Test
	void testToString() {
		p1 = new PersonalBookShelf(1, 2,0);
		
		assertEquals(p1.toString(), "PersonalBookShelf [userId=1, bookId=2, isRead=0]");
	}
	
}
