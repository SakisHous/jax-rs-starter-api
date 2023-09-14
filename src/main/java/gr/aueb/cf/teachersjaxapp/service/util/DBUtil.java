package gr.aueb.cf.teachersjaxapp.service.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Utility class that provides methods
 * to connect to the database.
 *
 * @author Thanasis Chousiadas
 */
public class DBUtil {
	private static BasicDataSource ds = new BasicDataSource();
	private static Connection connection;
	
	static {
		ds.setUrl("jdbc:mysql://localhost:3306/schooldb?serverTimezone=UTC");
		ds.setUsername(System.getenv("USER"));
		ds.setPassword(System.getenv("PASS"));
		ds.setInitialSize(8);
		ds.setMaxTotal(32);
		ds.setMinIdle(8);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(100);
	}
	
	/**
	 * No instances of this class should be available.
	 */
	private DBUtil() { }

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = ds.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
