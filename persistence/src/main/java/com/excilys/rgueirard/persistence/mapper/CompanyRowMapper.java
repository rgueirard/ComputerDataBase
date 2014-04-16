package com.excilys.rgueirard.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.rgueirard.domain.Company;

public class CompanyRowMapper implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Company company = null;
		company = Company.builder().id(Integer.parseInt(rs.getString(1)))
				.name(rs.getString(2)).build();
		return company;
	}

}
