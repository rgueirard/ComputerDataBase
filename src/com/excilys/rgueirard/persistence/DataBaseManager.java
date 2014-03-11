package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
	private static Connection connection;
	private String url = "jdbc:h2:~/test";
	private String user = "sa";
	private String password = "test";

	public static Connection getConnection() {

		if (connection == null) {

			new DataBaseManager();

		}

		return connection;

	}

	private DataBaseManager() {

		try {
			connection = DriverManager.getConnection(url, user, password);
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());

		}
	}
}
