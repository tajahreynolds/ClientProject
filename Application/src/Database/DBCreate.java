package Database;

import java.sql.*;

public class DBCreate {

	public void createBookTable() {
		String path = "jdbc:sqlite:Books.db";
		String query = "CREATE TABLE IF NOT EXISTS Book ("
				+ "bookId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "catalog VARCHAR(100) NOT NULL,"
				+ "title VARCHAR(100) NOT NULL,"
				+ "author VARCHAR(100) NOT NULL,"
				+ "publishDate VARCHAR(100) NOT NULL,"
				+ "pageCount INTEGER NOT NULL"
				+ ");";
		try {
			Connection conn = DriverManager.getConnection(path);
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createLoginTable() {
		String path = "jdbc:sqlite:Books.db";
		String query = "CREATE TABLE IF NOT EXISTS Login ("
				+ "userId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "userName VARCHAR(100) NOT NULL,"
				+ "email VARCHAR(100) NOT NULL,"
				+ "password VARCHAR(100) NOT NULL,"
				+ "userType VARCHAR(100) NOT NULL"
				+ ");";
		try {
			Connection conn = DriverManager.getConnection(path);
			Statement stmt = conn.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}		
	}
	
	public void createPersonalBookshelfTable() {
		String path = "jdbc:sqlite:Books.db";
		String query = "CREATE TABLE IF NOT EXISTS PersonalBookshelf ("
				+ "userId INTEGER,"
				+ "bookId INTEGER,"
				+ "isRead BIT NOT NULL"
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
