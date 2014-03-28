package com.excilys.rgueirard.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.rgueirard.domain.Company;
import com.excilys.rgueirard.persistence.CompanyDAO;
import com.excilys.rgueirard.persistence.DataBaseManager;

@Service
public class CompanyService {

	@Autowired
	private DataBaseManager dataBaseManager;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	public CompanyService() {
		super();
	}
	
	public Company retrieve(long id){
		
		Company company = null;
		
		try {
			company = companyDAO.retrieve(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
	
	public List<Company> retrieveAll() {
		List<Company> companies = null;
		try {
			companies = companyDAO.retrieveAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}
}
