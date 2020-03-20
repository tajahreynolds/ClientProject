package Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import Main.Books;
import Main.Catalogs;

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
				if(dataType.equals("Books")) {
					Books b = new Books(0, Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
					query = b.prepInsertQuery();
				} else if(dataType.equals("Catalogs")) {
					Catalogs c = new Catalogs(0, parts[0]);
					query = c.prepInsertQuery();
				}
				// Execute the query
				stmt.execute(query);
			}
			in.close();
			// NOT complete add error log
		} catch (FileNotFoundException e) {
			return false;
		} catch (SQLException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
		return true;		
	}
	
}
