package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DataBaseManager {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private final static String url = "jdbc:mysql://localhost:3306/MySQL_JDBC?zeroDateTimeBehavior=convertToNull";
	private final static String user = "root";
	private final static String password = "root";
	private static DataBaseManager dataBaseManager = null;
	private static BoneCP connectionPool;
	/* org.h2.Driver */
	/* sa    							  */
	/* test								  */
	/* jdbc:h2:~/test			  */
	
	private DataBaseManager() {
		
	}
	
	public static DataBaseManager getInstance() {
		if( dataBaseManager == null){
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(url);
			config.setUsername(user); 
			config.setPassword(password);
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			try {
				connectionPool = new BoneCP(config);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dataBaseManager = new DataBaseManager();
		}
		
		return dataBaseManager;
	}

	public Connection getConnection() {
		Connection connection = null;
		
		try {
			//connection = DriverManager.getConnection(url, user, password);
			connection = connectionPool.getConnection();
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return connection;
	}

	@Override
	protected void finalize() throws Throwable {
		connectionPool.shutdown(); 
		super.finalize();
	}

	

	

	
}
