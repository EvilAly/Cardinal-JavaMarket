package checkpointE;

import java.sql.*;
import java.util.*;

public class MarketDB {

	static Connection conn = null;
	static Statement stmt = null;
	private static Scanner scan = new Scanner(System.in);

	public static Connection createConnection() {

		String user = "root";
		String pass = "";
		String name = "cardinalmarket";
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/" + name;

		System.out.println(driver);
		System.out.println(url);

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection really is from : " + conn.getClass().getName());
			System.out.println("Connection successful!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
				// stmt.close();
				System.out.println("The connection was successfully closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void checkConnect() {
		if (conn == null) {
			conn = createConnection();
		}
		if (stmt == null) {
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				System.out.println("Cannot create the statement");
			}

		}
	}

	public static int getRunIDFromDB() {
		checkConnect();
		String query = "SELECT runID FROM fullcheckout";
		int id = 0;
		try {
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println("SQL Exception");
			e.printStackTrace();
		}
		return id;

	}

	public static void saveToDB(MultipleLanes lanes, SelfCheckouts selfServeLanes, ArrayList<Customer> customerList, int closeFullLanes, int closeSelfLanes, Integer[] open) {
		checkConnect();
		String full = lanes.toSQLString();
		if (closeFullLanes > open[0]) {
			full = full + ", -" + closeFullLanes + ")";
		} else
			full = full + ", +" + open[0] + ")";
		
		String self = selfServeLanes.toSQLString();
		if (closeSelfLanes > open[1]) {
			self = self + ", -" + closeSelfLanes + ")";
		} else
			self = self + ", +" + open[1] + ")";
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(full);
			System.out.println("Full Service Data has been saved.");
			stmt = conn.createStatement();
			stmt.executeUpdate(self);
			System.out.println("Self Service Data has been saved.");
			
			for (int i =0; i<customerList.size(); i++) {
				Customer c = customerList.get(i);
				String cust = c.toSQLString();
				stmt = conn.createStatement();
				stmt.executeUpdate(cust);
			}
			System.out.println("Customer Data has been saved.");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL insert Exception");
		}
		
	}
}