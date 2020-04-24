package Main;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		Catalog catalog = new Catalog(1, "books");
		
		Book book1 = new Book(1, 1, "Prey", "Michael Crichton", "November 25, 2002", 502);
		Book book2 = new Book(2, 2, "Prey", "Geoff Crichton", "November 25, 2001", 501);
		Book book3 = new Book(3, 3, "Prey", "Michael", "November 25, 2000", 500);
		
		catalog.addBook(book1);
		catalog.addBook(book2);
		catalog.addBook(book3);
		
		
		catalog.view();
		}
}
