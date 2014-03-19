package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.rgueirard.domain.Company;

public class CompanyDAO {

	private static CompanyDAO companyDAO = null;
	
	public CompanyDAO() {
		super();
	}
	
	public static CompanyDAO getInstance(){
		if( companyDAO == null){
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}

	public static void closeObject(PreparedStatement ps, ResultSet rs) {
		try {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Company retrieve(long id, Connection connection) throws SQLException{
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT id,name FROM company WHERE id = ?";
		Company company = null;
		
		ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		rs.next();
		company = Company.builder().id(Integer.parseInt(rs.getString(1))).name(rs.getString(2)).build();
	
		CompanyDAO.closeObject(ps, rs);
		
		return company;
	}

	public List<Company> retrieveAll(Connection connection) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Company> companies = new ArrayList<Company>();
		Company company;
		
		ps = connection
				.prepareStatement("SELECT id,name FROM company");

		rs = ps.executeQuery();
		while (rs.next()) {
			company = Company.builder()
					.id(Integer.parseInt(rs.getString(1)))
					.name(rs.getString(2)).build();
			companies.add(company);
		}

	
		CompanyDAO.closeObject(ps, rs);
		
		return companies;
	}

}
