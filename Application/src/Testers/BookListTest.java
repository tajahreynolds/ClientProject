package Testers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Objects.Book;

class BookListTest {
	
	private BookList l1;
	private Book b1 = new Book(1, "cat1", "Harry Potter", "JK Rowling", "Jan 2000", 500);
	private Book b2 = new Book(2, "cat1", "Lord of the Rings", "Tolkien", "Jan 1990", 5000);
	private Book b3 = new Book(2, "cat1", "Game of Thrones", "George Martin", "Jan 1995", 600);
	
	@Test
	void testBookList() {
		//create an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
	}
	
	@Test
	void testGetLength() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
		
		//Add to set and test 
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);
	}
	
	@Test
	void testIsEmpty() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
				
		//Add to set and test 
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);	
	}
	
	@Test
	void testAdd() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
						
		//Add to set and test 
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertTrue(l1.contains(b1));
		assertTrue(l1.contains(b2));
		assertTrue(l1.contains(b3));
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);
		
		//Add duplicate to set and test
		l1.add(b1);
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 4);	
	}
	
	@Test
	void testRemove() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
								
		//Add to set and test 
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertTrue(l1.contains(b1));
		assertTrue(l1.contains(b2));
		assertTrue(l1.contains(b3));
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);
		
		//Remove books and test one by one
		assertTrue(l1.remove(1).equals(b1));
		assertFalse(l1.contains(b1));
		assertEquals(l1.getLength(), 2);
		
		
		assertTrue(l1.remove(1).equals(b2));
		assertFalse(l1.contains(b2));
		assertEquals(l1.getLength(), 1);
		
		assertTrue(l1.remove(1).equals(b3));
		assertFalse(l1.contains(b3));
		assertEquals(l1.getLength(), 0);
		
		//Add to set and test 
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertTrue(l1.contains(b1));
		assertTrue(l1.contains(b2));
		assertTrue(l1.contains(b3));
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);
		
		//Remove in reverse order
		assertTrue(l1.remove(3).equals(b3));
		assertFalse(l1.contains(b3));
		assertEquals(l1.getLength(), 2);
		
		assertTrue(l1.remove(2).equals(b2));
		assertFalse(l1.contains(b2));
		assertEquals(l1.getLength(), 1);
		
		assertTrue(l1.remove(1).equals(b1));
		assertFalse(l1.contains(b1));
		assertEquals(l1.getLength(), 0);
	}
	
	@Test
	void testClear() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
										
		//Add to set and test 
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);
		
		//Clear and test
		l1.clear();
		assertTrue(l1.isEmpty());
		assertEquals(l1.getLength(), 0);
	}
	
	@Test
	void testReplace() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
												
		//Add to set and test 
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertTrue(l1.contains(b1));
		assertTrue(l1.contains(b2));
		assertTrue(l1.contains(b3));
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);
		
		//Replace and test
		assertTrue(b3.equals(l1.replace(3, b1)));
		assertFalse(l1.contains(b3));
		assertEquals(l1.getLength(), 3);
		
		assertTrue(b2.equals(l1.replace(2, b1)));
		assertFalse(l1.contains(b2));
		assertEquals(l1.getLength(), 3);
		
		assertTrue(b1.equals(l1.replace(1, b2)));
		assertTrue(l1.contains(b2));
		assertEquals(l1.getLength(), 3);
		
	}
	
	@Test
	void testGetEntry() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
		
		//Add to set and Test
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertTrue(l1.contains(b1));
		assertTrue(l1.contains(b2));
		assertTrue(l1.contains(b3));
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);
		
		//Test getEntry
		assertTrue(b1.equals(l1.getEntry(1)));
		assertTrue(b2.equals(l1.getEntry(2)));
		assertTrue(b3.equals(l1.getEntry(3)));
	}
	
	@Test
	void testToArray() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
				
		//Add to set and Test
		l1.add(b1);
		l1.add(b2);
		l1.add(b3);
		assertTrue(l1.contains(b1));
		assertTrue(l1.contains(b2));
		assertTrue(l1.contains(b3));
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 3);
		
		//Create array and test
		Object[] testArray = l1.toArray();
		assertEquals(testArray.length, l1.getLength());
		assertTrue(testArray[0].equals(l1.getEntry(1)));
		assertTrue(testArray[1].equals(l1.getEntry(2)));
		assertTrue(testArray[2].equals(l1.getEntry(3)));
	}
	
	@Test
	void testContains() {
		//create and test an empty list
		l1 = new BookList();
		assertTrue(l1.isEmpty());
		assertEquals(0, l1.getLength());
				
		//Add to set and Test
		l1.add(b1);
		l1.add(b2);
		assertTrue(l1.contains(b1));
		assertTrue(l1.contains(b2));
		assertFalse(l1.contains(b3));
		assertFalse(l1.isEmpty());
		assertEquals(l1.getLength(), 2);
	}
}
