package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogDAO {
private static LogDAO logDAO = null;
	
	public LogDAO() {
		super();
	}
	
	public static LogDAO getInstance(){
		if( logDAO == null){
			logDAO = new LogDAO();
		}
		return logDAO;
	}

	public static void closeObject(PreparedStatement ps, ResultSet rs) {
		try {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void create(long computerId, String timeS, String message) throws SQLException, ParseException {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		String query = "INSERT INTO log (id, computer_id, time, message) VALUES (0, ?, ?, ?)";
		PreparedStatement ps = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date timeUtil = null;
		java.sql.Date timeSql = null;
		
		ps = connection.prepareStatement(query);

		if ((timeS != null) && (timeS != "")) {
			timeUtil = formatter.parse(timeS);
			timeSql = new java.sql.Date(timeUtil.getTime());
		}
		
		ps.setLong(1, computerId);
		ps.setDate(2, timeSql);
		ps.setString(3, message);
		ps.executeUpdate();
		
		closeObject(ps, null);
	}
}
