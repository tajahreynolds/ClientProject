package Database;

import java.sql.*;

public class DBCreate {

	public void createBooksTable() {
		String path = "jdbc:sqlite:Books.db";
		String query = "CREATE TABLE IF NOT EXISTS Books ("
				+ "bookId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "catalogId INTEGER FOREIGN KEY REFERENCES Catalog(catalogId),"
				+ "title VARCHAR(100) NOT NULL,"
				+ "author VARCHAR(100) NOT NULL,"
				+ "publishDate DATE NOT NULL,"
				+ "pageCount int NOT NULL"
				+ ");";
		try {
			Connection conn = DriverManager.getConnection(path);
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createCatalogTable() {
		String path = "jdbc:sqlite:Books.db";
		String query = "CREATE TABLE IF NOT EXISTS Catalogs ("
				+ "catalogId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "catalog VARCHAR(100) NOT NULL"
				+ ");";
		try {
			Connection conn = DriverManager.getConnection(path);
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Add more table create function if needed
	
}
