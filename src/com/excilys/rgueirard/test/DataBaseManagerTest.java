package com.excilys.rgueirard.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.rgueirard.persistence.DataBaseManager;

public class DataBaseManagerTest {
	public static void main(String[] args) throws SQLException{
		Connection connection = DataBaseManager.getConnection();
		connection.toString();
		connection.close();
	}
}
