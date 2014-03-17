package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.rgueirard.domain.Company;

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
		Connection connection = dataBaseManager.getConnection();
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		return companyDAO.retrieve(id, connection);
	}
	
	public List<Company> retrieveAll() {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		CompanyDAO companyDAO = CompanyDAO.getInstance();
		return companyDAO.retrieveAll(connection);
	}
}
