package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public String FetchAllWithoutFilter(String table) {
		String query = "SELECT * FROM " + table;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String result = "";
			if(table.equals("Book")) {
				result = BookResultToString(rs);
			} else if(table.equals("Catalog")) {
				result = CatalogResultToString(rs);
			}
			return result;
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
		
		return null;
	}
	
	private String BookResultToString (ResultSet rs) {
		String result = "bookId\tcatalogId\ttitle\tauthor\tpublishDate\tpageCount\n";
		try {
			while(rs.next()) {
				result += rs.getString("bookId") + "\t";
				result += rs.getString("catalogId") + "\t";
				result += rs.getString("title") + "\t";
				result += rs.getString("author") + "\t";
				result += rs.getString("publishDate") + "\t";
				result += rs.getString("pageCount") + "\n";
			}
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
		return result;
	}
	
	private String CatalogResultToString (ResultSet rs) {
		String result = "catalogId\tcatalog\n";
		try {
			while(rs.next()) {
				result += rs.getString("catalogId") + "\t";
				result += rs.getString("catalog") + "\n";
			}
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
		return result;
	}
	
}
