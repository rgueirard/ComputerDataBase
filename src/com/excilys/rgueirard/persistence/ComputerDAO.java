package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.rgueirard.beans.Computer;

public class ComputerDAO {

	private ComputerDAO() {
		super();
	}

	public static List<Computer> getAllComputers() {
		Connection connection = DataBaseManager.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer;
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		
		try {
			ps = connection
					.prepareStatement("SELECT id,name,introduced,discontinued,company_id FROM COMPUTER");

			rs = ps.executeQuery();
			connection.close();
			while (rs.next()) {
				introduced = formatter.parse(rs.getString(3));
				discontinued = formatter.parse(rs.getString(4));
				computer = new Computer(Long.parseLong(rs.getString(1)), rs.getString(2), introduced, discontinued,
						Long.parseLong(rs.getString(5)));
				computers.add(computer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return computers;
	}
}
