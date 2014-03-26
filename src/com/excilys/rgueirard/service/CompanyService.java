package com.excilys.rgueirard.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.persistence.CompanyDAO;
import com.excilys.rgueirard.persistence.DataBaseManager;

public class CompanyService {

	private static CompanyService companyService = null;
	
	public CompanyService() {
		super();
	}
	
	public static CompanyService getInstance(){
		if( companyService == null){
			companyService = new CompanyService();
		}
		return companyService;
	}

	public Company retrieve(long id){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Company company = null;
		CompanyDAO companyDAO = dataBaseManager.getCompanyDAO();
		try {
			company = companyDAO.retrieve(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
	
	public List<Company> retrieveAll() {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		List<Company> companies = null;
		CompanyDAO companyDAO = dataBaseManager.getCompanyDAO();
		try {
			companies = companyDAO.retrieveAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
}
