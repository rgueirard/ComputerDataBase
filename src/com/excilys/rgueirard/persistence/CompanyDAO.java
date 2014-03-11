package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.rgueirard.beans.Company;

public class CompanyDAO {

	private CompanyDAO() {
		super();
	}
	
	public static List<Company> getAllCompanies() {
		Connection connection = DataBaseManager.getConnection();
		PreparedStatement ps;
		ResultSet rs;
		List<Company> companies = new ArrayList<Company>();
		Company company;
		try {
			ps = connection
					.prepareStatement("SELECT id,name,introduced,discontinued,company_id FROM COMPUTER");

			rs = ps.executeQuery();
			connection.close();
			while (rs.next()) {
				company = new Company(Integer.parseInt(rs.getString(1)),
						rs.getString(2));
				companies.add(company);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
	
}
