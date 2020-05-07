package Database;

import Objects.Book;
import Objects.Login;
import Objects.PersonalBookShelf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FetchData {

	private String path = "jdbc:sqlite:Books.db";
	public Connection conn;

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

	public List<Book> FetchAllWithFilter(String catalog) {
		String query = "SELECT * FROM Book WHERE catalog = '" + catalog + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return convert(rs);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
		return null;
	}
	
	public List<String> FetchCatalogList() {
		List<Book> bookList = FetchAllWithoutFilter();
		if(bookList == null)
			return null;
		List<String> catalogList = new ArrayList<String>();
		for (Book b : bookList) {
			if(!catalogList.contains(b.getCatalog())) {
				catalogList.add(b.getCatalog());
			}
		}
		return catalogList;
	}	
	
	// userId if success, -1 if failed
	public int verifyPassword(String userName, String password) {
		int ret = -1;
		String query = "SELECT * FROM Login WHERE userName = '" + userName + "' AND password = '" + password + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ret = rs.getInt("userId");
		} catch (SQLException e) {
			 new WriteExceptionToLog(e.getMessage());
			return -1;
		}
		return ret;
	}
	
	public String getUserType(String userName, String password) {
		String query = "SELECT * FROM Login WHERE userName = '" + userName + "' AND password = '" + password + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs.getString("userType");
		} catch (SQLException e) {
			 new WriteExceptionToLog(e.getMessage());
			return null;
		}
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
				if(bookIdList.contains(b.getBookId()))
					userBookList.add(b);
			}
			return userBookList;
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}

		return null;
	}
	
	public void RemoveFromPersonalBookshelf(int userId, int bookId) {
		String query = "DELETE FROM PersonalBookShelf WHERE userId = " + userId + " AND bookId = " + bookId;
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
	}
	
	public void RemoveBook(int bookId) {
		String query = "DELETE FROM Book WHERE bookId = " + bookId;
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
	}
	
	public void MarkBookAsRead(int userId, int bookId) {
		String query = "UPDATE PersonalBookShelf SET isRead = 1 WHERE userId = " + userId + " AND bookId = " + bookId;
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
	}
	
	public boolean IfBookReaded(int userId, int bookId) {
		String query = "SELECT * FROM PersonalBookShelf WHERE userId = " + userId + " AND bookId = " + bookId;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs.getInt("isRead") == 1;
		} catch (SQLException e) {
			// new WriteExceptionToLog(e.getMessage());
			return false;
		}
	}
	
	public boolean insertData(Object o, String dataType) {
		try {
			String query = null;
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
