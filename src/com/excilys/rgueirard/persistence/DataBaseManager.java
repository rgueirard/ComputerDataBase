package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.rgueirard.service.CompanyService;
import com.excilys.rgueirard.service.ComputerService;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

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
	private static DataBaseManager dataBaseManager = null;
	private static ThreadLocal<Connection> connection = new ThreadLocal<Connection>();
	private static BoneCP connectionPool = null;
	private static ComputerDAO computerDAO = null;
	private static ComputerService computerService = null;
	private static CompanyDAO companyDAO = null;
	private static CompanyService companyService = null;
	private static LogDAO logDAO = null;

	/* org.h2.Driver */
	/* sa */
	/* test */
	/* jdbc:h2:~/test */

	private DataBaseManager() {

	}

	public static DataBaseManager getInstance() {
		if (dataBaseManager == null) {
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
			computerDAO = ComputerDAO.getInstance();
			computerService = ComputerService.getInstance();
			companyDAO = CompanyDAO.getInstance();
			companyService = CompanyService.getInstance();
			logDAO = LogDAO.getInstance();
		}

		return dataBaseManager;
	}

	public Connection getConnection(){
		
		/*try {
			openConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}	*/
		//Connection connection = null;

		try {
			//connection = connectionPool.getConnection();
			connection.set(connectionPool.getConnection());
		}
		catch (SQLException e) {
		System.err.println(e.getMessage());
		}
		//return connection;
		return connection.get();
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public ComputerService getComputerService() {
		return computerService;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public LogDAO getLogDAO() {
		return logDAO;
	}

	@Override
	protected void finalize() throws Throwable {
		connectionPool.shutdown();
		super.finalize();
	}

	/*public void openConnection() throws SQLException {
		if (connection != null && connection.get() != null
				&& !connection.get().isClosed()) {
			connection.get().close();
		}
		connection.set(connectionPool.getConnection());
	}

	public void closeConnection() throws SQLException {

		if (connection != null && connection.get() != null
				&& !connection.get().isClosed()) {
			connection.get().close();
		}
	}*/

}
