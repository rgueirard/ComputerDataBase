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
	
	public void create(String timeS, String message, Connection connection) throws SQLException, ParseException {
		String query = "INSERT INTO log (id,time,message) VALUES (0,?,?)";
		PreparedStatement ps = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date timeUtil = null;
		java.sql.Date timeSql = null;
		
		ps = connection.prepareStatement(query);

		if ((timeS != null) && (timeS != "")) {
			timeUtil = formatter.parse(timeS);
			timeSql = new java.sql.Date(timeUtil.getTime());
		}
		
		ps.setDate(1, timeSql);
		ps.setString(2, message);
		ps.executeUpdate();
		
		closeObject(ps, null);
	}
}
