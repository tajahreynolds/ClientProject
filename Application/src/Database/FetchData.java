package Database;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

	// reference from https://stackoverflow.com/questions/6514876/most-efficient-conversion-of-resultset-to-json
	public JSONArray convert(ResultSet rs) {
		JSONArray json = new JSONArray();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			while(rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();

				for (int i=1; i<numColumns+1; i++) {
					String column_name = rsmd.getColumnName(i);

					if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
						obj.put(column_name, rs.getArray(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
						obj.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
						obj.put(column_name, rs.getBoolean(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
						obj.put(column_name, rs.getBlob(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
						obj.put(column_name, rs.getDouble(column_name)); 
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
						obj.put(column_name, rs.getFloat(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
						obj.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
						obj.put(column_name, rs.getNString(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
						obj.put(column_name, rs.getString(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
						obj.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
						obj.put(column_name, rs.getInt(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
						obj.put(column_name, rs.getDate(column_name));
					}
					else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
						obj.put(column_name, rs.getTimestamp(column_name));   
					}
					else{
						obj.put(column_name, rs.getObject(column_name));
					}
				}

				json.put(obj);
			}
		} catch (Exception e) {
			new WriteExceptionToLog(e.getMessage());
		}

		return json;
	}

	public JSONArray FetchAllWithoutFilter(String table) {
		String query = "SELECT * FROM " + table;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return convert(rs);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}

		return null;
	}

	public JSONArray verifyPassword(String userName, String password) {
		String query = "SELECT * FROM Login WHERE userName = '" + userName + "' AND PASSWORD = '" + password + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return convert(rs);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}
		return null;
	}

	public JSONArray FetchWithFilter(String table, String filterType, String text) {
		String query = "SELECT * FROM " + table + " WHERE " + filterType + " = '" + text + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return convert(rs);
		} catch (SQLException e) {
			new WriteExceptionToLog(e.getMessage());
		}

		return null;
	}
}
