
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class DbConnection {
	Connection conn = null;

	public void connect() {
		try {
			String url = "jdbc:postgresql://localhost/javapos";
			conn =  DriverManager.getConnection(url, "postgres", "baraza");
		} catch(SQLException ex) {
			System.out.println("SQL DB Connection error : " + ex);
		}
    }

	public String readItems() {
		JSONArray jaItems = new JSONArray();
		String query = "SELECT category_id, category_name, item_id, item_name, item_tag, purchase_price, sale_price "
			+ "FROM vw_items";
   		try {
			Statement stmt = conn.createStatement();
      		ResultSet rs = stmt.executeQuery(query);
      		while (rs.next()) {
				JSONObject jItem = new JSONObject();
        		jItem.put("category_id", rs.getString("category_id"));
				jItem.put("category_name", rs.getString("category_name"));
				jItem.put("item_id", rs.getString("item_id"));
				jItem.put("item_name", rs.getString("item_name"));

				jaItems.add(jItem);
        	}
    	} catch (SQLException ex) {
      		System.out.println("SQL DB Connection error : " + ex);
    	}

		return jaItems.toString();
	}

	public void close() {
		try {
			if(conn != null) conn.close();
		} catch(SQLException ex) {
			System.out.println("SQL DB Connection error : " + ex);
		}
    }
}
