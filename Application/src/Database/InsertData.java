package Database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import Main.Book;
import Main.Catalog;

public class InsertData {

	public boolean insertData(File inputFile, String dataType) {
		try {
			Scanner in = new Scanner(inputFile);
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Books.db");
			Statement stmt = conn.createStatement();
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String[] parts = line.split("\t");
				String query = "";

				// Define the data type and insert into the correct table
				if(dataType.equals("Book")) {
					Book b = new Book(0, Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
					query = b.prepInsertQuery();
				} else if(dataType.equals("Catalog")) {
					Catalog c = new Catalog(0, parts[0]);
					query = c.prepInsertQuery();
				}
				// Execute the query
				stmt.execute(query);
			}
			in.close();
		} catch (Exception e) {
			new WriteExceptionToLog(e.getMessage());
			return false;
		}
		return true;		
	}

	public boolean insertData(Object o, String dataType) {
		try {
			String query = null;
			Connection conn = DriverManager.getConnection("jdbc:sqlite:Books.db");
			Statement stmt = conn.createStatement();
			// Define the data type and insert into the correct table
			if(dataType.equals("Book")) {
				Book b = (Book) o;
				query = b.prepInsertQuery();
			} else if(dataType.equals("Catalog")) {
				Catalog c = (Catalog) o;
				query = c.prepInsertQuery();
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
