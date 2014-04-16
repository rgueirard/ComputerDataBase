package com.excilys.rgueirard.persistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.persistence.mapper.ComputerRowMapper;
import com.excilys.rgueirard.wrapper.PageWrapper;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class ComputerDAO{

	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	@Autowired
	private BoneCPDataSource dataBaseManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final String ORDER = "ORDER BY :orderby ";
	private final String ASC = "ASC ";
	private final String DESC = "DESC ";
	private final String LIMIT = "LIMIT :offset, :nbdisplay ";
	private final String INNERJOINCPY = "INNER JOIN company AS cpy ";
	private final String OUTERJOINCPY = "LEFT JOIN company AS cpy ";


	public ComputerDAO() {
		super();
	}

	public long delete(long id) throws SQLException {
		logger.debug("ComputerDAO : deletion d'ordinateur");
		String query = "DELETE FROM computer WHERE id = :id";
		namedParameterJdbcTemplate.update(query,new MapSqlParameterSource("id",id));
		return id;
	}

	public long update(Computer computer) throws SQLException, ParseException {
		logger.debug("ComputerDAO : edition d'ordinateur");
		String query = "UPDATE computer SET name=:name, introduced=:introduced, discontinued=:discontinued, company_id=:cpnid WHERE id=:id";
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		if (computer.getIntroduced() != null) {
			introducedSql = new java.sql.Date(computer.getIntroduced()
					.getMillis());
		}
		if (computer.getDiscontinued() != null) {
			discontinuedSql = new java.sql.Date(computer.getDiscontinued()
					.getMillis());
		}
		
		namedParameters.addValue("id",computer.getId());
		namedParameters.addValue("name", computer.getName());
		namedParameters.addValue("introduced", introducedSql);
		namedParameters.addValue("discontinued", discontinuedSql);
		if (computer.getCompany() != null) {
			namedParameters.addValue("cpnid",computer.getCompany().getId());
		} else {
			namedParameters.addValue("cpnid",new Long(null));
		}
		
		namedParameterJdbcTemplate.update(query,namedParameters);

		return computer.getId();
	}

	public long create(Computer computer) throws SQLException, ParseException {
		logger.debug("ComputerDAO : creation d'ordinateur");
		String query = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (0,:name,:introduced,:discontinued,:cpnid)";
		java.sql.Date introducedSql = null;
		java.sql.Date discontinuedSql = null;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		Long id = null;
		
		if (computer.getIntroduced() != null) {
			introducedSql = new java.sql.Date(computer.getIntroduced()
					.getMillis());
		}
		if (computer.getDiscontinued() != null) {
			discontinuedSql = new java.sql.Date(computer.getDiscontinued()
					.getMillis());
		}		
		
		namedParameters.addValue("name",computer.getName());
		namedParameters.addValue("introduced",introducedSql);
		namedParameters.addValue("discontinued",discontinuedSql);
		if (computer.getCompany() != null) {
			id = computer.getCompany().getId();
		}
		namedParameters.addValue("cpnid",id);
		namedParameterJdbcTemplate.update(query,namedParameters,keyHolder);
		
		return keyHolder.getKey().longValue();
	}

	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper)
			throws SQLException, ParseException {
		logger.debug("ComputerDAO : recherche d'ordinateurs");
		
		
		StringBuilder query = new StringBuilder(
				"SELECT cpt.id,cpt.name,cpt.introduced,cpt.discontinued,cpt.company_id,cpy.name FROM computer AS cpt ");
		StringBuilder sizeQuery = new StringBuilder(
				"SELECT count(*) FROM computer AS cpt ");
		List<Computer> computers = new ArrayList<Computer>();
		int count = 0;

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

			count = jdbcTemplate.queryForObject(sizeQuery.toString(), Integer.class);
			
			if (count != 0) {
				wrapper.setSize(count);
			}

			/* recuperation de nbPages */
			wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
					/ wrapper.getNbDisplay()));
			
			if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
				wrapper.setCurrentPage(wrapper.getNbPages() - 1);
			}
			
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("orderby", wrapper.getOrderBy());		
			namedParameters.addValue("offset", (wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
			namedParameters.addValue("nbdisplay", wrapper.getNbDisplay());
			computers = namedParameterJdbcTemplate.query(query.toString(), namedParameters, new ComputerRowMapper());
			
			wrapper.setPages(computers);

			/* retrieve by name, by company, by id */
		} else {
			switch (wrapper.getSearchType()) {
			/* by name */
			case 0:
				sizeQuery.append(OUTERJOINCPY);
				sizeQuery.append("ON company_id=cpy.id WHERE cpt.name LIKE :searchmotif ");
				query.append(OUTERJOINCPY);
				query.append("ON company_id=cpy.id WHERE cpt.name LIKE :searchmotif ");
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
				sizeQuery.append("WHERE company_id=cpy.id AND cpy.name LIKE :searchmotif ");
				query.append(INNERJOINCPY);
				query.append("WHERE company_id=cpy.id AND cpy.name LIKE :searchmotif ");
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
			count = namedParameterJdbcTemplate.queryForObject(sizeQuery.toString(), new MapSqlParameterSource("searchmotif" ,"%" + wrapper.getSearchMotif() + "%"), Integer.class);
			
			if (count != 0) {
				wrapper.setSize(count);
			}
			
			if (wrapper.getSize() != 0) {
				/* recuperation des computers */
						/* recuperation de nbPages */
				wrapper.setNbPages((int) Math.ceil(wrapper.getSize() * 1.0
						/ wrapper.getNbDisplay()));
				
				if (wrapper.getCurrentPage() > wrapper.getNbPages()) {
					wrapper.setCurrentPage(wrapper.getNbPages() - 1);
				}
				
				MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				namedParameters.addValue("searchmotif", "%" + wrapper.getSearchMotif() + "%");
				namedParameters.addValue("orderby", wrapper.getOrderBy());
				namedParameters.addValue("offset", (wrapper.getCurrentPage() - 1) * wrapper.getNbDisplay());
				namedParameters.addValue("nbdisplay", wrapper.getNbDisplay());
				
				computers = namedParameterJdbcTemplate.query(query.toString(), namedParameters, new ComputerRowMapper());
				
				wrapper.setPages(computers);
			}
		}
		return wrapper;
	}

	public Computer retrieveById(long id) throws SQLException, ParseException {
		logger.debug("ComputerDAO : recherche d'ordinateur par id");
		Computer computer = null;
		StringBuilder query = new StringBuilder(
				"SELECT cpt.id,cpt.name,cpt.introduced,cpt.discontinued,cpt.company_id,cpy.name FROM computer AS cpt LEFT JOIN company AS cpy ON company_id=cpy.id WHERE cpt.id = :id ");

		computer = namedParameterJdbcTemplate.queryForObject(query.toString(), new MapSqlParameterSource("id", id), new ComputerRowMapper());
		
		return computer;
	}
}
