package Main;

import Database.DBCreate;
import Database.FetchData;
import Objects.Book;
import Objects.Login;
import Objects.PersonalBookShelf;

public class Driver {

	public static void main(String[] args) {

		// catalog    title    author   date  pageCount
//		Book book1 = new Book(1, "Autobiography", "Roughing It", "Mark Twain", "1872-02-18", 608);
//		Book book2 = new Book(2, "Fiction", "The Gilded Age", "Mark Twain", "1873", 630);
//		Book book3 = new Book(2, "Autobiography", "The Diary Of a Young Girl", "Anne Frank", "1947-06-25", -1);
		Book book1 = new Book(2, "Autobiography", "Becoming", "Michelle Obama", "2018-11-13", 448);

		
		DBCreate b = new DBCreate();
		b.createBookTable();
		b.createLoginTable();
		b.createPersonalBookshelfTable();
		PersonalBookShelf p = new PersonalBookShelf(3, 1, 0);
//		System.out.println(i.insertData(p, "PersonalBookShelf"));
		
//		i.insertData(c1, "Catalog");
//		System.out.println(i.insertData(book1, "Book"));
//		System.out.println(i.insertData(book2, "Book"));
//		System.out.println(i.insertData(book3, "Book"));
		
		Login l = new Login(-1, "admin", "admin@miamioh.edu", "admin", "admin");
		FetchData f = new FetchData();
//		f.insertData(l, "Login");
		System.out.println(f.FetchByUserId(2));
	}
}