package com.excilys.rgueirard.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.persistence.mapper.CompanyRowMapper;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository
public class CompanyDAO {

	public CompanyDAO() {
		super();
	}

	@Autowired
	private BoneCPDataSource dataBaseManager;
		
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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

	public Company retrieve(long id) throws SQLException {
		Company company = null;
		String query = "SELECT id,name FROM company WHERE id = :id";
		company = namedParameterJdbcTemplate.queryForObject(query,new MapSqlParameterSource("id",id),new CompanyRowMapper());	
		return company;
	}

	public List<Company> retrieveAll() throws SQLException {
		List<Company> companies = new ArrayList<Company>();
		String query = "SELECT id,name FROM company";
		companies = jdbcTemplate.query(query,new CompanyRowMapper());
		return companies;
	}

}
