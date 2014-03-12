package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private final static String url = "jdbc:mysql://localhost:3306/MySQL_JDBC?zeroDateTimeBehavior=convertToNull";
	private final static String user = "root";
	private final static String password = "root";
	private static DataBaseManager dataBaseManager = null;	
	/* org.h2.Driver */
	/* sa    							  */
	/* test								  */
	/* jdbc:h2:~/test			  */
	private DataBaseManager() {
	}
	
	public static DataBaseManager getInstance() {
		if( dataBaseManager == null){
			dataBaseManager = new DataBaseManager();
		}
		
		return dataBaseManager;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return connection;
	}



	

	
}
