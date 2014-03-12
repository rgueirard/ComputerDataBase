package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.rgueirard.domain.Company;

public class CompanyDAO {

	private CompanyDAO() {
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
	
	public static Company retrieve(long id){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name FROM company WHERE id = ?";
		Company company = null;
		
		try {
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			rs.next();
			company = Company.builder().id(Integer.parseInt(rs.getString(1))).name(rs.getString(2)).build();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CompanyDAO.closeObject(ps, rs, connection);
		}
		
		return company;
	}

	public static List<Company> retrieveAll() {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Company> companies = new ArrayList<Company>();
		Company company;
		try {
			ps = connection
					.prepareStatement("SELECT id,name FROM company");

			rs = ps.executeQuery();
			while (rs.next()) {
				company = Company.builder()
						.id(Integer.parseInt(rs.getString(1)))
						.name(rs.getString(2)).build();
				companies.add(company);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//CompanyDAO.closeObject(ps, rs, connection);
		}
		return companies;
	}

}
