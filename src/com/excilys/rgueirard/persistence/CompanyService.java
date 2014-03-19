package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
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
		Connection connection = null;
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		Company company = null;
		
		try {
			connection = dataBaseManager.getConnection();
			connection.setAutoCommit(false);
			CompanyDAO companyDAO = CompanyDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			company = companyDAO.retrieve(id, connection);
			logDAO.create(dateSql.toString(), "create", connection);
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} catch (ParseException e) {
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
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		List<Company> companies = null;

		try {
			connection = dataBaseManager.getConnection();
			connection.setAutoCommit(false);
			CompanyDAO companyDAO = CompanyDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			companies = companyDAO.retrieveAll(connection);
			logDAO.create(dateSql.toString(), "create", connection);
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} catch (ParseException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} finally{
			   try{connection.close();}catch(Exception e){e.printStackTrace();}
		}
		return companies;
	}
}
