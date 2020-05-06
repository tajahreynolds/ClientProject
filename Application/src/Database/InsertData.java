package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import Main.Book;
import Main.Login;
import Main.PersonalBookShelf;

public class InsertData {

	public boolean insertData(Object o, String dataType) {
		try {
			String query = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Books.db");
			Statement stmt = conn.createStatement();
			// Define the data type and insert into the correct table
			if(dataType.equals("Book")) {
				Book b = (Book) o;
				query = b.prepInsertQuery();
			} else if(dataType.equals("Login")) {
				Login l = (Login) o;
				query = l.prepInsertQuery();
			} else if(dataType.equals("PersonalBookShelf")) {
				PersonalBookShelf p = (PersonalBookShelf) o;
				query = p.prepInsertQuery();
			} else {
				new WriteExceptionToLog("Wrong Datatype");
			}
			// Execute the query
			stmt.execute(query);
		} catch (Exception e) {
			new WriteExceptionToLog(e.getMessage());
			return false;
		}
		return true;		
	}


}
