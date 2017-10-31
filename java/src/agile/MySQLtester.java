package agile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;



public class MySQLtester {

	public static void main(String[] args) {
		DataSource ds = ConnectionManager.getMySQLDataSource();
		ResultSet rs = null;
		
		try (Connection con = ds.getConnection();
			 Statement stmt = con.createStatement(); ) {
			rs = stmt.executeQuery("select word from words where word like '%jack%'");
			while(rs.next()){
				System.out.println(rs.getString("word"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}

