package com.excilys.rgueirard.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.rgueirard.persistence.DataBaseManager;

public class DataBaseManagerTest {
	
	private final static Logger logger = LoggerFactory.getLogger(DataBaseManager.class);
	
	public static void main(String[] args) throws SQLException{
		BasicConfigurator.configure();
		
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		
		logger.info("{}\n",connection.toString());
		
		connection.close();
	}
}
