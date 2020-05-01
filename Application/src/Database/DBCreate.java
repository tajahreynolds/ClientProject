package Database;

import java.sql.*;

public class DBCreate {

	public void createBookTable() {
		String path = "jdbc:sqlite:Books.db";
		String query = "CREATE TABLE IF NOT EXISTS Book ("
				+ "bookId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "catalogId INTEGER,"
				+ "title VARCHAR(100) NOT NULL,"
				+ "author VARCHAR(100) NOT NULL,"
				+ "publishDate DATE NOT NULL,"
				+ "pageCount INTEGER NOT NULL,"
				+ "FOREIGN KEY (catalogId) REFERENCES Catalog(catalogId)"
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
		String query = "CREATE TABLE IF NOT EXISTS Catalog ("
				+ "catalogId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "catalog VARCHAR(100) NOT NULL"
				+ ");";
		try {
			Connection conn = DriverManager.getConnection(path);
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
	}

	
	// Add more table create function if needed
	
}
