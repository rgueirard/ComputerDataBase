package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Component
public class DataBaseManager {
	static {
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
	
	private static ThreadLocal<Connection> connection;
	private static BoneCP connectionPool = null;

	public DataBaseManager() {
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
		
		connection = new ThreadLocal<Connection>();
	}

	public Connection getConnection() throws SQLException{
		if(connection.get()==null){
			openConnection();
		}
		return connection.get();
	}

	public void openConnection() throws SQLException {
		if ((connection != null)&&(connection.get() != null)&&(!connection.get().isClosed())) {
			connection.get().close();
		}
		connection.set(connectionPool.getConnection());
	}

	public void closeConnection() throws SQLException {
		if ((connection != null)&&(connection.get() != null)&&(!connection.get().isClosed())) {
			connection.get().close();
		}
		connection.remove();
	}

}
