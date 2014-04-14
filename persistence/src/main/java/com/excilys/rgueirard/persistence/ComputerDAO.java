package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.wrapper.PageWrapper;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class ComputerDAO {

	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Autowired
	private BoneCPDataSource dataBaseManager;

	private final String ORDER = "ORDER BY ? ";
	private final String ASC = "ASC ";
	private final String DESC = "DESC ";
	private final String LIMIT = "LIMIT ?, ? ";
	private final String INNERJOINCPY = "INNER JOIN company AS cpy ";
	private final String OUTERJOINCPY = "LEFT JOIN company AS cpy ";
	private final String WHERENAME = "WHERE cpt.name LIKE ? ";
	private final String WHEREID = "WHERE cpt.id = ? ";

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

	public long delete(long id) throws SQLException {
		logger.debug("ComputerDAO : deletion d'ordinateur");
		Connection connection = DataSourceUtils.getConnection(dataBaseManager);
		String query = "DELETE FROM computer WHERE id = ?";
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ps.executeUpdate();

		closeObject(ps, null);

		return id;
	}

	public long update(Computer computer) throws SQLException, ParseException {
		logger.debug("ComputerDAO : edition d'ordinateur");
		Connection connection = DataSourceUtils.getConnection(dataBaseManager);
		PreparedStatement ps = null;
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;

		if (computer.getIntroduced() != null) {
			introducedSql = new java.sql.Date(computer.getIntroduced()
					.getMillis());
		}
		if (computer.getDiscontinued() != null) {
			discontinuedSql = new java.sql.Date(computer.getDiscontinued()
					.getMillis());
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
		logger.debug("ComputerDAO : creation d'ordinateur");
		Connection connection = DataSourceUtils.getConnection(dataBaseManager);
		String query = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (0,?,?,?,?)";
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;

		ps = connection
				.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		if (computer.getIntroduced() != null) {
			introducedSql = new java.sql.Date(computer.getIntroduced()
					.getMillis());
		}
		if (computer.getDiscontinued() != null) {
			discontinuedSql = new java.sql.Date(computer.getDiscontinued()
					.getMillis());
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

	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper)
			throws SQLException, ParseException {
		logger.debug("ComputerDAO : recherche d'ordinateurs");
		Connection connection = DataSourceUtils.getConnection(dataBaseManager);
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder query = new StringBuilder(
				"SELECT cpt.id,cpt.name,cpt.introduced,cpt.discontinued,cpt.company_id,cpy.name FROM computer AS cpt ");
		StringBuilder sizeQuery = new StringBuilder(
				"SELECT count(*) FROM computer AS cpt ");
		List<Computer> computers = new ArrayList<Computer>();
		Computer computer = null;
		DateTime introduced = null;
		DateTime discontinued = null;
		Company company = null;

		/* retrieve all */
		if (wrapper.getSearchMotif().isEmpty()) {
			query.append(OUTERJOINCPY);
			query.append("ON company_id=cpy.id ");
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
					introduced = new DateTime(rs.getDate(3));
				} else {
					introduced = null;
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = new DateTime(rs.getDate(4));
				} else {
					discontinued = null;
				}
				if (rs.getString(5) != null) {
					// company = companyDAO.retrieve(Long.parseLong(rs
					// .getString(5)));
					company = Company.builder()
							.id(Long.parseLong(rs.getString(5)))
							.name(rs.getString(6)).build();
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
				sizeQuery.append(OUTERJOINCPY);
				sizeQuery.append("ON company_id=cpy.id WHERE cpt.name LIKE ? ");
				query.append(OUTERJOINCPY);
				query.append("ON company_id=cpy.id WHERE cpt.name LIKE ? ");
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
				sizeQuery.append(INNERJOINCPY);
				sizeQuery.append("WHERE company_id=cpy.id AND cpy.name LIKE ?");
				query.append(INNERJOINCPY);
				query.append("WHERE company_id=cpy.id AND cpy.name LIKE ? ");
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
			logger.debug("size : " + sizeQuery + "\nQuery : " + query + "\n");
			rs = ps.executeQuery();
			rs.next();
			if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
				wrapper.setSize(Integer.parseInt(rs.getString(1)));
			}
			if (wrapper.getSize() != 0) {
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
						introduced = new DateTime(rs.getDate(3));
					} else {
						introduced = null;
					}
					if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
						discontinued = new DateTime(rs.getDate(4));
					} else {
						discontinued = null;
					}
					if (rs.getString(5) != null) {
						// company = companyDAO.retrieve(Long.parseLong(rs
						// .getString(5)));
						company = Company.builder()
								.id(Long.parseLong(rs.getString(5)))
								.name(rs.getString(6)).build();
					} else {
						company = null;
					}
					computer = Computer.builder()
							.id(Long.parseLong(rs.getString(1)))
							.name(rs.getString(2)).introduced(introduced)
							.discontinued(discontinued).company(company)
							.build();
					computers.add(computer);
				}
				wrapper.setPages(computers);
			}
		}
		this.closeObject(ps, rs);
		return wrapper;
	}

	public Computer retrieveById(long id) throws SQLException, ParseException {

		logger.debug("ComputerDAO : recherche d'ordinateur par id");
		Connection connection = DataSourceUtils.getConnection(dataBaseManager);
		Computer computer = null;
		DateTime introduced = null;
		DateTime discontinued = null;
		Company company = null;
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuilder query = new StringBuilder(
				"SELECT cpt.id,cpt.name,cpt.introduced,cpt.discontinued,cpt.company_id,cpy.name FROM computer AS cpt LEFT JOIN company AS cpy ON company_id=cpy.id WHERE cpt.id = ? ");

		ps = connection.prepareStatement(query.toString());
		ps.setLong(1, id);
		rs = ps.executeQuery();
		rs.next();
		if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
			introduced = DateTime.parse(rs.getString(3), formatter);
		} else {
			introduced = null;
		}
		if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
			discontinued = DateTime.parse(rs.getString(4), formatter);
		} else {
			discontinued = null;
		}
		if (rs.getString(5) != null) {
			// company =
			// companyService.retrieve(Long.parseLong(rs.getString(5)));
			company = Company.builder().id(Long.parseLong(rs.getString(5)))
					.name(rs.getString(6)).build();
		} else {
			company = null;
		}
		computer = Computer.builder().id(Long.parseLong(rs.getString(1)))
				.name(rs.getString(2)).introduced(introduced)
				.discontinued(discontinued).company(company).build();

		return computer;
	}
}
