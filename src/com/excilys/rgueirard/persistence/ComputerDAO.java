package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;

public class ComputerDAO {

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
		PreparedStatement ps = null;
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
		long id = Long.parseLong(idS);
		long companyId = Long.parseLong(companyIdS);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date introducedUtil = null;
		java.util.Date discontinuedUtil = null;
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;
		
		try {
			if ((introducedS != null) && (introducedS != "")) {
				introducedUtil = formatter.parse(introducedS);
				introducedSql = new java.sql.Date(introducedUtil.getTime());
			}
			if ((discontinuedS != null) && (discontinuedS != "")) {
				discontinuedUtil = formatter.parse(discontinuedS);
				discontinuedSql = new java.sql.Date(discontinuedUtil.getTime());
			}
			
			ps = connection.prepareStatement(query);

			
			ps.setString(1, nameS);
			ps.setDate(2, introducedSql);
			ps.setDate(3, discontinuedSql);
			if(companyId != 0){
				ps.setLong(4, companyId);
			} else {
				ps.setNull(4, Types.BIGINT);
			}
			ps.setLong(5, id);
			
			ps.executeUpdate();
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeObject(ps, null, connection);
		}
		
	}

	public static void create(String name, String introducedDate,
			String discontinuedDate, String company) {

		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		String query = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (0,?,?,?,?)";
		PreparedStatement ps = null;
		java.util.Date introducedUtil = null;
		java.util.Date discontinuedUtil = null;
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;
		long companyId = Long.parseLong(company);
		

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			ps = connection.prepareStatement(query);
			
			if ((introducedDate != null) && (introducedDate != "")) {
				introducedUtil = formatter.parse(introducedDate);
				introducedSql = new java.sql.Date(introducedUtil.getTime());
			}
			if ((discontinuedDate != null) && (discontinuedDate != "")) {
				discontinuedUtil = formatter.parse(discontinuedDate);
				discontinuedSql = new java.sql.Date(discontinuedUtil.getTime());
			}
			
			//ps.setLong(1, id);
			ps.setString(1, name);
			ps.setDate(2, introducedSql);
			ps.setDate(3, discontinuedSql);

			if(companyId != 0){
				ps.setLong(4, companyId);
			} else {
				ps.setNull(4, Types.BIGINT);
			}

			ps.executeUpdate();
			//nextId = nextId + 1;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeObject(ps, null, connection);
		}
	}
	
	public static Computer retrieve(String idS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id = ? ORDER BY ?";
		long id = Long.parseLong(idS);
		Computer computer = new Computer();	
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;
				
		try {
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			ps.setInt(2, orderBy);
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
	
	public static List<Computer> retrieveByName(String name, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE name LIKE ? ORDER BY ?";
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();	
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;
				
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "%"+name+"%");
			ps.setInt(2, orderBy);
			rs = ps.executeQuery();
			while (rs.next()) {
				if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
					introduced = formatter.parse(rs.getString(3));
				} else {
					introduced = null;
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = formatter.parse(rs.getString(4));
				} else {
					discontinued = null;
				}
				if (rs.getString(5) != null) {
					company = CompanyDAO.retrieve(Long.parseLong(rs
							.getString(5)));
				} else {
					company = null;
				}
				computer = Computer.builder()
						.id(Long.parseLong(rs.getString(1)))
						.name(rs.getString(2)).introduced(introduced)
						.discontinued(discontinued).company(company).build();
				computers.add(computer);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ComputerDAO.closeObject(ps, rs, connection);
		}
		return computers;	
	}
	
	public static List<Computer> retrieveByCompany(String companyName, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id FROM computer INNER JOIN company WHERE computer.company_id=company.id AND company.name LIKE ? ORDER BY ?";
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();	
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;
				
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "%"+companyName+"%");
			ps.setInt(2, orderBy);
			rs = ps.executeQuery();
			while (rs.next()) {
				if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
					introduced = formatter.parse(rs.getString(3));
				} else {
					introduced = null;
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = formatter.parse(rs.getString(4));
				} else {
					discontinued = null;
				}
				if (rs.getString(5) != null) {
					company = CompanyDAO.retrieve(Long.parseLong(rs
							.getString(5)));
				} else {
					company = null;
				}
				computer = Computer.builder()
						.id(Long.parseLong(rs.getString(1)))
						.name(rs.getString(2)).introduced(introduced)
						.discontinued(discontinued).company(company).build();
				computers.add(computer);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ComputerDAO.closeObject(ps, rs, connection);
		}
		return computers;	
	}

	public static List<Computer> retrieveByIntroduced(String introducedS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE introduced = ? ORDER BY ?";
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();	
		Date introduced = null;
		java.sql.Date introducedSql = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;
				
		try {
			ps = connection.prepareStatement(query);
			
			if ((introducedS != null) && (introducedS != "")) {
				introduced = formatter.parse(introducedS);
				introducedSql = new java.sql.Date(introduced.getTime());
			}
			
			ps.setDate(1, introducedSql);
			ps.setInt(2, orderBy);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
					introduced = formatter.parse(rs.getString(3));
				} else {
					introduced = null;
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = formatter.parse(rs.getString(4));
				} else {
					discontinued = null;
				}
				if (rs.getString(5) != null) {
					company = CompanyDAO.retrieve(Long.parseLong(rs
							.getString(5)));
				} else {
					company = null;
				}
				computer = Computer.builder()
						.id(Long.parseLong(rs.getString(1)))
						.name(rs.getString(2)).introduced(introduced)
						.discontinued(discontinued).company(company).build();
				computers.add(computer);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ComputerDAO.closeObject(ps, rs, connection);
		}
		return computers;	
	}
	
	public static List<Computer> retrieveByDiscontinued(String discontinuedS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE discontinued = ? ORDER BY ?";
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();	
		Date introduced = null;
		java.sql.Date discontinuedSql = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;
				
		try {
			ps = connection.prepareStatement(query);
			
			if ((discontinuedS != null) && (discontinuedS != "")) {
				discontinued = formatter.parse(discontinuedS);
				discontinuedSql = new java.sql.Date(introduced.getTime());
			}
			
			ps.setDate(1, discontinuedSql);
			ps.setInt(2, orderBy);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
					introduced = formatter.parse(rs.getString(3));
				} else {
					introduced = null;
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = formatter.parse(rs.getString(4));
				} else {
					discontinued = null;
				}
				if (rs.getString(5) != null) {
					company = CompanyDAO.retrieve(Long.parseLong(rs
							.getString(5)));
				} else {
					company = null;
				}
				computer = Computer.builder()
						.id(Long.parseLong(rs.getString(1)))
						.name(rs.getString(2)).introduced(introduced)
						.discontinued(discontinued).company(company).build();
				computers.add(computer);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ComputerDAO.closeObject(ps, rs, connection);
		}
		return computers;	
	}
	
	public static List<Computer> retrieveAll(int orderBy) {
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
					.prepareStatement("SELECT id,name,introduced,discontinued,company_id FROM computer ORDER BY ?");

			ps.setInt(1, orderBy);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
					introduced = formatter.parse(rs.getString(3));
				} else {
					introduced = null;
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = formatter.parse(rs.getString(4));
				} else {
					discontinued = null;
				}
				if (rs.getString(5) != null) {
					company = CompanyDAO.retrieve(Long.parseLong(rs
							.getString(5)));
				} else {
					company = null;
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
