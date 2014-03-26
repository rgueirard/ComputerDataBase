package com.excilys.rgueirard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.persistence.ComputerDAO;
import com.excilys.rgueirard.persistence.DataBaseManager;
import com.excilys.rgueirard.persistence.LogDAO;
import com.excilys.rgueirard.wrapper.PageWrapper;

public class ComputerService {
	private static ComputerService computerService = null;	
	
	private ComputerService() {
		super();
	}
	
	public static ComputerService getInstance(){
		if( computerService == null){
			computerService = new ComputerService();
		}
		return computerService;
	}
	
	public void create(Computer computer) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		LogDAO logDAO = LogDAO.getInstance();
		Connection connection = null;
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		long id = 0;
		
		try {
			connection = dataBaseManager.getConnection();
			connection.setAutoCommit(false);
			id = computerDAO.create(computer);
			logDAO.create(id, dateSql.toString(), "create");
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} catch (ParseException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} finally{
			try {dataBaseManager.closeConnection();}catch(SQLException e){e.printStackTrace();}
		}
	}

	public void update(Computer computer) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		LogDAO logDAO = LogDAO.getInstance();
		Connection connection = null;
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		long id = 0;
		
		try {
			connection = dataBaseManager.getConnection();
			connection.setAutoCommit(false);
			id = computerDAO.update(computer);
			logDAO.create(id, dateSql.toString(), "update");
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} catch (ParseException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} finally{
			try {dataBaseManager.closeConnection();}catch(SQLException e){e.printStackTrace();}
		}
	}
	
	public void delete(String idString){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		LogDAO logDAO = LogDAO.getInstance();
		Connection connection = null;
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		long id = 0;
					
		try {
			connection = dataBaseManager.getConnection();
			connection.setAutoCommit(false);
			id = computerDAO.delete(idString);
			logDAO.create(id, dateSql.toString(), "delete");
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} catch (ParseException e) {
			try {connection.rollback();}catch(SQLException e1){e.printStackTrace();}
			e.printStackTrace();
		} finally{
			try {dataBaseManager.closeConnection();}catch(SQLException e){e.printStackTrace();}
		}
	}
	
	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		ComputerDAO computerDAO = dataBaseManager.getComputerDAO();
		CompanyService companyService = dataBaseManager.getCompanyService();
		try {
			wrapper = computerDAO.retrieve(wrapper, companyService);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return wrapper;
	}
	
	public Computer retrieveById(long id) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		ComputerDAO computerDAO = dataBaseManager.getComputerDAO();
		CompanyService companyService = dataBaseManager.getCompanyService();
		Computer computer = null;
		try {
			computer = computerDAO.retrieveById(id, companyService);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return computer;		
	}
}
