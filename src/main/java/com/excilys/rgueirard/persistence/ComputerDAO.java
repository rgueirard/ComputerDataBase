package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.service.CompanyService;
import com.excilys.rgueirard.wrapper.PageWrapper;

@Repository
public class ComputerDAO {

	@Autowired
	DataBaseManager dataBaseManager;	
	
	
	private final String ORDER = "ORDER BY ? ";
	private final String ASC = "ASC ";
	private final String DESC = "DESC ";
	private final String LIMIT = "LIMIT ?, ? ";
	private final String WHERENAME = "WHERE name LIKE ? ";
	private final String WHEREID = "WHERE id = ? ";

	public ComputerDAO() {
		super();
	}

	public void closeObject(PreparedStatement ps, ResultSet rs)
			throws SQLException {

		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
	}

	public long delete(String idString) throws SQLException {
		
		Connection connection = dataBaseManager.getConnection();
		String query = "DELETE FROM computer WHERE id = ?";
		long id = Long.parseLong(idString);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ps.executeUpdate();

		closeObject(ps, null);

		return id;
	}

	public long update(Computer computer) throws SQLException, ParseException {
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;

		if (computer.getIntroduced() != null) {
			introducedSql = new java.sql.Date(computer.getIntroduced()
					.getTime());
		}
		if (computer.getDiscontinued() != null) {
			discontinuedSql = new java.sql.Date(computer.getDiscontinued()
					.getTime());
		}

		ps = connection.prepareStatement(query);

		ps.setString(1, computer.getName());
		ps.setDate(2, introducedSql);
		ps.setDate(3, discontinuedSql);
		if (computer.getCompany() != null) {
			ps.setLong(4, computer.getCompany().getId());
		} else {
			ps.setNull(4, Types.BIGINT);
		}

		ps.setLong(5, computer.getId());
		ps.executeUpdate();
		closeObject(ps, null);

		return computer.getId();
	}

	public long create(Computer computer) throws SQLException, ParseException {
		Connection connection = dataBaseManager.getConnection();
		String query = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (0,?,?,?,?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;

		ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		if (computer.getIntroduced() != null) {
			introducedSql = new java.sql.Date(computer.getIntroduced()
					.getTime());
		}
		if (computer.getDiscontinued() != null) {
			discontinuedSql = new java.sql.Date(computer.getDiscontinued()
					.getTime());
		}

		ps.setString(1, computer.getName());
		ps.setDate(2, introducedSql);
		ps.setDate(3, discontinuedSql);

		if (computer.getCompany() != null) {
			ps.setLong(4, computer.getCompany().getId());
		} else {
			ps.setNull(4, Types.BIGINT);
		}

		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		rs.next();
		if (rs.getString(1) != null) {
			computer.setId(Long.parseLong(rs.getString(1)));
		}

		closeObject(ps, rs);

		return computer.getId();
	}

	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper,
			CompanyService companyService) throws SQLException, ParseException {
		Connection connection = dataBaseManager.getConnection();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder query = new StringBuilder(
				"SELECT cpt.id,cpt.name,cpt.introduced,cpt.discontinued,cpt.company_id FROM computer AS cpt ");
		StringBuilder sizeQuery = new StringBuilder(
				"SELECT count(*) FROM computer ");
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = null;
		Date introduced = null;
		Date discontinued = null;
		Company company = null;

		/* retrieve all */
		if (wrapper.getSearchMotif().isEmpty()) {
			query.append(ORDER);
			if (wrapper.isAscendant()) {
				query.append(ASC);
			} else {
				query.append(DESC);
			}
			query.append(LIMIT);

			ps = connection.prepareStatement(sizeQuery.toString());
			rs = ps.executeQuery();
			rs.next();
			if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
				wrapper.setSize(Integer.parseInt(rs.getString(1)));
			}

			/* recuperation de nbPages */
			wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
					/ wrapper.getNbDisplay()));

			ps = connection.prepareStatement(query.toString());
			ps.setInt(1, wrapper.getOrderBy());

			if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
				wrapper.setCurrentPage(wrapper.getNbPages() - 1);
			}

			ps.setInt(2,
					(wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
			ps.setInt(3, wrapper.getNbDisplay());

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
			wrapper.setPages(computers);

			/* retrieve by name, by company, by id */
		} else {
			switch (wrapper.getSearchType()) {
			/* by name */
			case 0:
				sizeQuery.append(WHERENAME);
				query.append(WHERENAME);
				query.append(ORDER);
				if (wrapper.isAscendant()) {
					query.append(ASC);
				} else {
					query.append(DESC);
				}
				query.append(LIMIT);
				break;
			/* by company */
			case 1:
				sizeQuery
						.append("INNER JOIN company AS cpy WHERE company_id=cpy.id AND cpy.name LIKE ?");
				query.append("INNER JOIN company AS cpy WHERE company_id=cpy.id AND cpy.name LIKE ? ");
				query.append(ORDER);
				if (wrapper.isAscendant()) {
					query.append(ASC);
				} else {
					query.append(DESC);
				}
				query.append(LIMIT);
				break;
			}

			/* recupération de la taille */
			ps = connection.prepareStatement(sizeQuery.toString());

			ps.setString(1, "%" + wrapper.getSearchMotif() + "%");

			rs = ps.executeQuery();
			rs.next();
			if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
				wrapper.setSize(Integer.parseInt(rs.getString(1)));
			}

			/* recuperation des computers */
			ps = connection.prepareStatement(query.toString());

			ps.setString(1, "%" + wrapper.getSearchMotif() + "%");

			/* recuperation de nbPages */
			wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
					/ wrapper.getNbDisplay()));

			ps.setInt(2, wrapper.getOrderBy());

			if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
				wrapper.setCurrentPage(wrapper.getNbPages() - 1);
			}
			ps.setInt(3,
					(wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
			ps.setInt(4, wrapper.getNbDisplay());

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
			wrapper.setPages(computers);
		}

		this.closeObject(ps, rs);
		return wrapper;
	}

	public Computer retrieveById(long id, CompanyService companyService)
			throws SQLException, ParseException {
		Connection connection = dataBaseManager.getConnection();
		Computer computer = null;
		Date introduced = null;
		Date discontinued = null;
		Company company = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuilder query = new StringBuilder(
				"SELECT cpt.id,cpt.name,cpt.introduced,cpt.discontinued,cpt.company_id FROM computer AS cpt ");
		query.append(WHEREID);

		ps = connection.prepareStatement(query.toString());
		ps.setLong(1, id);
		rs = ps.executeQuery();
		rs.next();
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
			company = companyService.retrieve(Long.parseLong(rs.getString(5)));
		} else {
			company = null;
		}
		computer = Computer.builder().id(Long.parseLong(rs.getString(1)))
				.name(rs.getString(2)).introduced(introduced)
				.discontinued(discontinued).company(company).build();

		return computer;
	}
}
