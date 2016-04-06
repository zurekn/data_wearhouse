package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnexion {

	private static final String USER_NAME = "BOB";
	private static final String PASSWORD = "Cyborgv7";
	private static final String IP = "localhost";
	private static final String DATABASE = "oracle";

	private Connection connection;
	
	public OracleConnexion(){
		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");

		connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@"+IP+":1521:"+DATABASE, USER_NAME,
					PASSWORD);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.err.println("Failed to make connection!");
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
}
