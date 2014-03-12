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

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;

public class ComputerDAO {

	private static long nextId;
	static {
		List<Computer> computers = ComputerDAO.retrieveAll();
		ComputerDAO.nextId = computers.get(computers.size() - 1).getId();
	}

	private ComputerDAO() {
		super();
	}

	public static void closeObject(PreparedStatement ps, ResultSet rs,
			Connection connection) {
		try {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
			if(connection != null){
				connection.close();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(String idString){
		
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		String query = "DELETE FROM computer WHERE id = ?";
		long id = Long.parseLong(idString);
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeObject(ps, null, connection);
		}
		
	}
	
	public static void update(String idS, String nameS, String introducedS, String discontinuedS, String companyIdS){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement ps = null;
		/* A FAIRE */
		
		
	}

	public static void create(String name, String introducedDate,
			String discontinuedDate, String company) {

		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		String query = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (?,?,?,?,?)";
		PreparedStatement ps = null;
		long id = nextId + 1;
		java.util.Date introducedUtil = null;
		java.util.Date discontinuedUtil = null;
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;
		long companyId = Long.parseLong(company);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			if ((introducedDate != null) && (introducedDate != "")) {
				introducedUtil = formatter.parse(introducedDate);
				introducedSql = new java.sql.Date(introducedUtil.getTime());
			}
			if ((discontinuedDate != null) && (discontinuedDate != "")) {
				discontinuedUtil = formatter.parse(discontinuedDate);
				discontinuedSql = new java.sql.Date(discontinuedUtil.getTime());
			}
			
			ps = connection.prepareStatement(query);

			ps.setLong(1, id);
			ps.setString(2, name);
			ps.setDate(3, introducedSql);
			ps.setDate(4, discontinuedSql);
			ps.setLong(5, companyId);

			ps.executeUpdate();
			nextId = nextId + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeObject(ps, null, connection);
		}
	}
	
	public static Computer retrieve(String idS){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id = ?";
		long id = Long.parseLong(idS);
		Computer computer = new Computer();	
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;
				
		try {
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
				introduced = formatter.parse(rs.getString(3));
			}
			if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
				discontinued = formatter.parse(rs.getString(4));
			}
			if (rs.getString(5) != null) {
				company = CompanyDAO.retrieve(Long.parseLong(rs
						.getString(5)));
			}
			computer = Computer.builder()
					.id(Long.parseLong(rs.getString(1)))
					.name(rs.getString(2)).introduced(introduced)
					.discontinued(discontinued).company(company).build();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ComputerDAO.closeObject(ps, rs, connection);
		}
		return computer;	
	}

	public static List<Computer> retrieveAll() {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer;
		Company company = null;
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		// "yyyy-MM-dd HH:mm:ss.S"
		try {
			ps = connection
					.prepareStatement("SELECT id,name,introduced,discontinued,company_id FROM computer");

			rs = ps.executeQuery();

			while (rs.next()) {
				if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
					introduced = formatter.parse(rs.getString(3));
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = formatter.parse(rs.getString(4));
				}
				if (rs.getString(5) != null) {
					company = CompanyDAO.retrieve(Long.parseLong(rs
							.getString(5)));
				}
				computer = Computer.builder()
						.id(Long.parseLong(rs.getString(1)))
						.name(rs.getString(2)).introduced(introduced)
						.discontinued(discontinued).company(company).build();
				computers.add(computer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			ComputerDAO.closeObject(ps, rs, connection);
		}
		return computers;
	}
}
