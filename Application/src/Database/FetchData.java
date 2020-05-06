package Database;

import Main.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FetchData {

	private String path = "jdbc:sqlite:Books.db";
	Connection conn;

	public FetchData() {
		try {
			conn = DriverManager.getConnection(path);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
	}

	public List<Book> convert(ResultSet rs) {
		List<Book> list = new ArrayList<Book>();
		try {
			while(rs.next()) {
				Book b = new Book(rs.getInt("bookId"), rs.getString("catalog"), 
						rs.getString("title"), rs.getString("author"), rs.getString("publishDate"), rs.getInt("pageCount"));
				list.add(b);
			}
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
		return list;
	}

	public List<Integer> getBookIdList(ResultSet rs) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			while(rs.next()) {
				list.add(rs.getInt("bookId"));
			}
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
		return list;
	}
	
	public List<Book> FetchAllWithoutFilter() {
		String query = "SELECT * FROM Book";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return convert(rs);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}

		return null;
	}
	
	// userId if success, -1 if failed
	public int verifyPassword(String userName, String password) {
		String query = "SELECT * FROM Login WHERE userName = '" + userName + "' AND password = '" + password + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs.getInt("userId");
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
		return -1;
	}
	
	public List<Book> FetchByUserId (int userId) {
		String query = "SELECT * FROM PersonalBookShelf WHERE userId = " + userId;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			List<Integer> bookIdList = getBookIdList(rs);
			List<Book> allBookList = FetchAllWithoutFilter();
			List<Book> userBookList = new ArrayList<>();
			for (Book b : allBookList) {
				if(bookIdList.contains(b.getBookId()));
				userBookList.add(b);
			}
			return userBookList;
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}

		return null;
	}
}
