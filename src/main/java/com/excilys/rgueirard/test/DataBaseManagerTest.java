package com.excilys.rgueirard.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.rgueirard.persistence.DataBaseManager;


public class DataBaseManagerTest {
	
	private final static Logger logger = LoggerFactory.getLogger(DataBaseManager.class);
	
	@Autowired
	private DataBaseManager dataBaseManager;
	
	public void main(String[] args) throws SQLException{
		Connection connection = dataBaseManager.getConnection();
		
		logger.info("{}\n",connection.toString());
		
		connection.close();
	}
}
