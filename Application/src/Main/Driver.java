package Main;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		BookList<Books> bookList = new BookList<Books>();
		
		Books book1 = new Books(1, 1, "Prey", "Michael Crichton", "November 25, 2002", 502);
		Books book2 = new Books(2, 2, "Prey", "Geoff Crichton", "November 25, 2001", 501);
		Books book3 = new Books(3, 3, "Prey", "Michael", "November 25, 2000", 500);
		
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		
		
		System.out.println(bookList.contains(book2));
		}
}
