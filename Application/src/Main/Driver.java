package Main;

import Database.DBCreate;
import Database.FetchData;
import Database.InsertData;

public class Driver {

	public static void main(String[] args) {

		Book book1 = new Book(1, "Science", "Prey", "Michael Crichton", "2015-12-17", 502);
		Book book2 = new Book(2, "Terr", "Prey", "Michael Crichton", "2015-12-17", 502);

		
		DBCreate b = new DBCreate();
		b.createBookTable();
		b.createLoginTable();
		b.createPersonalBookshelfTable();
		
		System.out.println(book1.prepInsertQuery());
		
		InsertData i = new InsertData();
		
//		i.insertData(c1, "Catalog");
//		Login l = new Login(1, "Fred", "wangt24", "123456", "ADMIN");
		System.out.println(i.insertData(book1, "Book"));
//		System.out.println(i.insertData(book2, "Book"));
		
		
		FetchData f = new FetchData();
		System.out.println(f.FetchAllWithoutFilter("Book"));
		System.out.println(f.FetchWithFilter("Book", "catalog", "Science"));
	}
}