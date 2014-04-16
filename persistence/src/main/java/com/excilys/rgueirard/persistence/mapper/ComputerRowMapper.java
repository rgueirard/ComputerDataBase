package com.excilys.rgueirard.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.domain.Computer;

public class ComputerRowMapper implements RowMapper<Computer>{

	@Override
	public Computer mapRow(ResultSet rs, int rowNumber) throws SQLException {
		DateTime introduced = null;
		DateTime discontinued = null;
		Computer computer = null;
		Company company = null;
		//DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
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
		/*if ((rs.getString(3) != null) && (rs.getString(3) != "")) {
			introduced = DateTime.parse(rs.getString(3), formatter);
		} else {
			introduced = null;
		}
		if ((rs.getString(4) != null) && (rs.getString(4) != "")) {
			discontinued = DateTime.parse(rs.getString(4), formatter);
		} else {
			discontinued = null;
		}*/
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
		
		return computer;
	}

}
