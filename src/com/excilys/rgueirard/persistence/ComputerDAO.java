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
import com.excilys.rgueirard.domain.ComputerWrapper;

public class ComputerDAO {

	private static ComputerDAO computerDAO = null;

	private ComputerDAO() {
		super();
	}

	public static ComputerDAO getInstance() {
		if (computerDAO == null) {
			computerDAO = new ComputerDAO();
		}
		return computerDAO;
	}

	public void closeObject(PreparedStatement ps, ResultSet rs) throws SQLException {

		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
	}

	public void delete(String idString, Connection connection) throws SQLException {

		String query = "DELETE FROM computer WHERE id = ?";
		long id = Long.parseLong(idString);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ps.executeUpdate();
		
		closeObject(ps, null);


	}

	public void update(String idS, String nameS, String introducedS,
			String discontinuedS, String companyIdS, Connection connection) throws SQLException, ParseException {
		PreparedStatement ps = null;
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
		long id = Long.parseLong(idS);
		long companyId = Long.parseLong(companyIdS);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date introducedUtil = null;
		java.util.Date discontinuedUtil = null;
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;


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
		if (companyId != 0) {
			ps.setLong(4, companyId);
		} else {
			ps.setNull(4, Types.BIGINT);
		}
		ps.setLong(5, id);
		ps.executeUpdate();
		closeObject(ps, null);
	}

	public void create(String name, String introducedDate,
			String discontinuedDate, String company, Connection connection) throws SQLException, ParseException {

		String query = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (0,?,?,?,?)";
		PreparedStatement ps = null;
		java.util.Date introducedUtil = null;
		java.util.Date discontinuedUtil = null;
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;
		long companyId = Long.parseLong(company);

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		ps = connection.prepareStatement(query);

		if ((introducedDate != null) && (introducedDate != "")) {
			introducedUtil = formatter.parse(introducedDate);
			introducedSql = new java.sql.Date(introducedUtil.getTime());
		}
		if ((discontinuedDate != null) && (discontinuedDate != "")) {
			discontinuedUtil = formatter.parse(discontinuedDate);
			discontinuedSql = new java.sql.Date(discontinuedUtil.getTime());
		}

		ps.setString(1, name);
		ps.setDate(2, introducedSql);
		ps.setDate(3, discontinuedSql);

		if (companyId != 0) {
			ps.setLong(4, companyId);
		} else {
			ps.setNull(4, Types.BIGINT);
		}

		ps.executeUpdate();
		
		closeObject(ps, null);
	
	}

	public ComputerWrapper retrieve(String idS, int orderBy, int offset, int nbDisplay, Connection connection) throws SQLException, ParseException {
		CompanyService companyService = CompanyService.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id = ? ORDER BY ? LIMIT ?, ?";
		String sizeQuery = "SELECT count(*) FROM computer WHERE id = ?";
		ComputerWrapper wrapper = null;
		int size = 0;
		Computer computer = new Computer();
		long id = Long.parseLong(idS);
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;

		ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ps.setInt(2, orderBy);
		ps.setInt(3, offset);
		ps.setInt(4, nbDisplay);
		rs = ps.executeQuery();
		rs.next();
		if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
			introduced = formatter.parse(rs.getString(3));
		}
		if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
			discontinued = formatter.parse(rs.getString(4));
		}
		if (rs.getString(5) != null) {
			company = companyService.retrieve(Long.parseLong(rs
					.getString(5)));
		}
		computer = Computer.builder().id(Long.parseLong(rs.getString(1)))
				.name(rs.getString(2)).introduced(introduced)
				.discontinued(discontinued).company(company).build();
		
		ps = connection.prepareStatement(sizeQuery);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		rs.next();
		if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
			size = Integer.parseInt(rs.getString(1));
		}
		
		this.closeObject(ps, rs);
		
		wrapper = ComputerWrapper.builder().computer(computer).size(size).build();
		
		return wrapper;
	}

	public ComputerWrapper retrieveByName(String name, int orderBy, int offset, int nbDisplay,
			Connection connection) throws SQLException, ParseException {
		CompanyService companyService = CompanyService.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE name LIKE ? ORDER BY ? LIMIT ?, ?";
		String sizeQuery = "SELECT count(*) FROM computer WHERE name LIKE ?";
		ComputerWrapper wrapper = null;
		int size = 0;
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, "%" + name + "%");
		ps.setInt(2, orderBy);
		ps.setInt(3, offset);
		ps.setInt(4, nbDisplay);
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
				company = companyService.retrieve(Long.parseLong(rs
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
		
		ps = connection.prepareStatement(sizeQuery);
		ps.setString(1,  "%" + name + "%");
		rs = ps.executeQuery();
		rs.next();
		if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
			size = Integer.parseInt(rs.getString(1));
		}
		
		this.closeObject(ps, rs);
		
		wrapper = ComputerWrapper.builder().computers(computers).size(size).build();
		
		return wrapper;
	}

	public ComputerWrapper retrieveByCompany(String companyName, int orderBy, int offset, int nbDisplay,
			Connection connection) throws SQLException, ParseException {
		CompanyService companyService = CompanyService.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id FROM computer INNER JOIN company WHERE computer.company_id=company.id AND company.name LIKE ? ORDER BY ? LIMIT ?, ?";
		String sizeQuery = "SELECT count(*) FROM computer INNER JOIN company WHERE computer.company_id=company.id AND company.name LIKE ?";
		ComputerWrapper wrapper = null;
		int size = 0;
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;

		ps = connection.prepareStatement(query);
		ps.setString(1, "%" + companyName + "%");
		ps.setInt(2, orderBy);
		ps.setInt(3, offset);
		ps.setInt(4, nbDisplay);
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
				company = companyService.retrieve(Long.parseLong(rs
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
		
		ps = connection.prepareStatement(sizeQuery);
		ps.setString(1, "%" + companyName + "%");
		rs = ps.executeQuery();
		rs.next();
		if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
			size = Integer.parseInt(rs.getString(1));
		}		
		
		this.closeObject(ps, rs);
		
		wrapper = ComputerWrapper.builder().computers(computers).size(size).build();
		
		return wrapper;
	}

	public ComputerWrapper retrieveByIntroduced(String introducedS, int orderBy, int offset, int nbDisplay,
			Connection connection) throws NumberFormatException, SQLException, ParseException {
		CompanyService companyService = CompanyService.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE introduced = ? ORDER BY ? LIMIT ?, ?";
		String sizeQuery = "SELECT count(*) FROM computer WHERE introduced = ?";
		ComputerWrapper wrapper = null;
		int size = 0;
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();
		Date introduced = null;
		java.sql.Date introducedSql = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;

		ps = connection.prepareStatement(query);

		if ((introducedS != null) && (introducedS != "")) {
			introduced = formatter.parse(introducedS);
			introducedSql = new java.sql.Date(introduced.getTime());
		}

		ps.setDate(1, introducedSql);
		ps.setInt(2, orderBy);
		ps.setInt(3, offset);
		ps.setInt(4, nbDisplay);
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
				company = companyService.retrieve(Long.parseLong(rs
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

		ps = connection.prepareStatement(sizeQuery);
		ps.setDate(1, introducedSql);
		rs = ps.executeQuery();
		rs.next();
		if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
			size = Integer.parseInt(rs.getString(1));
		}		
		
		this.closeObject(ps, rs);
		
		wrapper = ComputerWrapper.builder().computers(computers).size(size).build();
		
		return wrapper;
	}

	public ComputerWrapper retrieveByDiscontinued(String discontinuedS,
			int orderBy, int offset, int nbDisplay, Connection connection) throws NumberFormatException, SQLException, ParseException {
		CompanyService companyService = CompanyService.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE discontinued = ? ORDER BY ? LIMIT ?, ?";
		String sizeQuery = "SELECT count(*) FROM computer WHERE discontinued = ?";
		ComputerWrapper wrapper = null;
		int size = 0;
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = new Computer();
		Date introduced = null;
		java.sql.Date discontinuedSql = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Company company = null;

		ps = connection.prepareStatement(query);

		if ((discontinuedS != null) && (discontinuedS != "")) {
			discontinued = formatter.parse(discontinuedS);
			discontinuedSql = new java.sql.Date(discontinued.getTime());
		}

		ps.setDate(1, discontinuedSql);
		ps.setInt(2, orderBy);
		ps.setInt(3, offset);
		ps.setInt(4, nbDisplay);
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
				company = companyService.retrieve(Long.parseLong(rs
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
	
		ps = connection.prepareStatement(sizeQuery);
		ps.setDate(1, discontinuedSql);
		rs = ps.executeQuery();
		rs.next();
		if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
			size = Integer.parseInt(rs.getString(1));
		}		
		
		this.closeObject(ps, rs);
		
		wrapper = ComputerWrapper.builder().computers(computers).size(size).build();
		
		return wrapper;
	}

	public ComputerWrapper retrieveAll(int orderBy, int offset, int nbDisplay, Connection connection) throws NumberFormatException, SQLException, ParseException {
		CompanyService companyService = CompanyService.getInstance();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer ORDER BY ? LIMIT ?, ?";
		String sizeQuery = "SELECT count(*) FROM computer";
		ComputerWrapper wrapper = null;
		int size = 0;
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer;
		Company company = null;
		Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		ps = connection
				.prepareStatement(query);

		ps.setInt(1, orderBy);
		ps.setInt(2, offset);
		ps.setInt(3, nbDisplay);
		
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
				company = companyService.retrieve(Long.parseLong(rs
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
		
		ps = connection.prepareStatement(sizeQuery);
		rs = ps.executeQuery();
		rs.next();
		if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
			size = Integer.parseInt(rs.getString(1));
		}		
		
		this.closeObject(ps, rs);
		
		wrapper = ComputerWrapper.builder().computers(computers).size(size).build();
		
		return wrapper;
	}
}
