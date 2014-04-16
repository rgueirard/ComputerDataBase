package com.excilys.rgueirard.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class LogDAO {

	public LogDAO() {
		super();
	}

	@Autowired
	private BoneCPDataSource dataBaseManager;

	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public static void closeObject(PreparedStatement ps, ResultSet rs) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create(long computerId, String timeS, String message)
			throws SQLException, ParseException {
		String query = "INSERT INTO log (id, computer_id, time, message) VALUES (0, :cptid, :time, :message)";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date timeUtil = null;
		java.sql.Date timeSql = null;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		if ((timeS != null) && (timeS != "")) {
			timeUtil = formatter.parse(timeS);
			timeSql = new java.sql.Date(timeUtil.getTime());
		}
		
		namedParameters.addValue("cptid",computerId);
		namedParameters.addValue("time", timeSql);
		namedParameters.addValue("message", message);
		namedParameterJdbcTemplate.update(query,namedParameters);
	}
}
