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
import java.util.List;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.ComputerDTO;
import com.excilys.rgueirard.domain.PageWrapper;
import com.excilys.rgueirard.service.CompanyService;

public class ComputerDAO {

	private static ComputerDAO computerDAO = null;
	private final String ORDER = "ORDER BY ? ";
	private final String ASC = "ASC ";
	private final String DESC = "DESC ";
	private final String LIMIT = "LIMIT ?, ? ";
	private final String WHERENAME = "WHERE name LIKE ? ";
	private final String WHEREID = "WHERE id = ? ";

	private ComputerDAO() {
		super();
	}

	public static ComputerDAO getInstance() {
		if (computerDAO == null) {
			computerDAO = new ComputerDAO();
		}
		return computerDAO;
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

	public void delete(String idString, Connection connection)
			throws SQLException {

		String query = "DELETE FROM computer WHERE id = ?";
		long id = Long.parseLong(idString);
		PreparedStatement ps = null;

		ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ps.executeUpdate();

		closeObject(ps, null);

	}

	public void update(String idS, String nameS, String introducedS,
			String discontinuedS, String companyIdS, Connection connection)
			throws SQLException, ParseException {
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
			String discontinuedDate, String company, Connection connection)
			throws SQLException, ParseException {

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

	public PageWrapper<ComputerDTO> retrieve(PageWrapper<ComputerDTO> wrapper,
			CompanyService companyService, Connection connection)
			throws SQLException, ParseException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder query = new StringBuilder(
				"SELECT cpt.id,cpt.name,cpt.introduced,cpt.discontinued,cpt.company_id FROM computer AS cpt ");
		StringBuilder sizeQuery = new StringBuilder(
				"SELECT count(*) FROM computer ");
		List<ComputerDTO> computers = new ArrayList<ComputerDTO>();
		//Computer computer = new Computer();
		ComputerDTO computerDTO = null;
		long companyId = 0;
		String companyName = null;
		Company company = null;
		/*Date introduced = null;
		Date discontinued = null;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");*/

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
				/*if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
					introduced = formatter.parse(rs.getString(3));
				} else {
					introduced = null;
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = formatter.parse(rs.getString(4));
				} else {
					discontinued = null;
				}*/
				if (rs.getString(5) != null) {
					company = companyService.retrieve(Long.parseLong(rs
							.getString(5)));
					companyId = company.getId();
					companyName = company.getName();
				} else {
					company = null;
					companyId = 0;
					companyName = null;
				}
				computerDTO = ComputerDTO.builder()
						.id(Long.parseLong(rs.getString(1)))
						.name(rs.getString(2)).introduced(rs.getString(3))
						.discontinued(rs.getString(4))
						.companyId(companyId)
						.companyName(companyName)
						.build();
				computers.add(computerDTO);
			}
			wrapper.setPages(computers);

		} else {
			switch (wrapper.getSearchType()) {
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
			case 2:
				sizeQuery.append(WHEREID);
				query.append(WHEREID);
				break;
			}

			/* recupération de la taille */
			ps = connection.prepareStatement(sizeQuery.toString());
			if (wrapper.getSearchType() != 2) {
				ps.setString(1, "%" + wrapper.getSearchMotif() + "%");
			} else {
				ps.setLong(1, Long.parseLong(wrapper.getSearchMotif()));
			}

			rs = ps.executeQuery();
			rs.next();
			if ((rs.getString(1) != null) && (rs.getString(1) != "")) {
				wrapper.setSize(Integer.parseInt(rs.getString(1)));
			}

			/* recuperation des computers */
			ps = connection.prepareStatement(query.toString());

			if (wrapper.getSearchType() != 2) {
				ps.setString(1, "%" + wrapper.getSearchMotif() + "%");
			} else {
				ps.setLong(1, Long.parseLong(wrapper.getSearchMotif()));
			}

			/* recuperation de nbPages */
			if (wrapper.getSearchType() != 2) {
				wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
						/ wrapper.getNbDisplay()));

				ps.setInt(2, wrapper.getOrderBy());

				if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
					wrapper.setCurrentPage(wrapper.getNbPages() - 1);
				}
				ps.setInt(3,
						(wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
				ps.setInt(4, wrapper.getNbDisplay());
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				/*if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
					introduced = formatter.parse(rs.getString(3));
				} else {
					introduced = null;
				}
				if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
					discontinued = formatter.parse(rs.getString(4));
				} else {
					discontinued = null;
				}*/
				if (rs.getString(5) != null) {
					company = companyService.retrieve(Long.parseLong(rs
							.getString(5)));
					companyId = company.getId();
					companyName = company.getName();
				} else {
					company = null;
					companyName = null;
				}
				computerDTO = ComputerDTO.builder()
						.id(Long.parseLong(rs.getString(1)))
						.name(rs.getString(2)).introduced(rs.getString(3))
						.discontinued(rs.getString(4)).companyId(companyId).companyName(companyName).build();
				computers.add(computerDTO);
			}
			wrapper.setPages(computers);
		}

		this.closeObject(ps, rs);
		return wrapper;
	}
}
