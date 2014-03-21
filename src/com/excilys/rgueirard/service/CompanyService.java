package com.excilys.rgueirard.service;

import java.sql.Connection;
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
		Connection connection = null;
		//Date dateUtil = new Date();
		//java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		Company company = null;
		
		try {
			connection = dataBaseManager.getConnection();
			connection.setAutoCommit(false);
			CompanyDAO companyDAO = dataBaseManager.getCompanyDAO();
			//LogDAO logDAO = dataBaseManager.getLogDAO();
			company = companyDAO.retrieve(id, connection);
			//logDAO.create(0, dateSql.toString(), "retrieveCompany", connection);
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} finally{
			   try{connection.close();}catch(Exception e){e.printStackTrace();}
		}
		return company;
	}
	
	public List<Company> retrieveAll() {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = null;
		//Date dateUtil = new Date();
		//java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		List<Company> companies = null;

		try {
			connection = dataBaseManager.getConnection();
			connection.setAutoCommit(false);
			CompanyDAO companyDAO = dataBaseManager.getCompanyDAO();
			//LogDAO logDAO = dataBaseManager.getLogDAO();
			companies = companyDAO.retrieveAll(connection);
			//logDAO.create(0, dateSql.toString(), "retrieveCompanies", connection);
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} finally{
			   try{connection.close();}catch(Exception e){e.printStackTrace();}
		}
		return companies;
	}
}
