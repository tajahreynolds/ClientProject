package Main;

import Database.DBCreate;
import Database.FetchData;
import Database.InsertData;

public class Driver {

	public static void main(String[] args) {

		Book book1 = new Book(1, 1, "Prey", "Michael Crichton", "2015-12-17", 502);

		Catalog c1 = new Catalog(0, "Test");
		
		DBCreate b = new DBCreate();
		b.createCatalogTable();
		b.createBookTable();
		System.out.println(book1.prepInsertQuery());
		
		InsertData i = new InsertData();
		
		i.insertData(c1, "Catalog");
		
		
		FetchData f = new FetchData();
		System.out.println(f.FetchAllWithoutFilter("Book"));
		System.out.println(f.FetchAllWithoutFilter("Catalog"));
	}
}
