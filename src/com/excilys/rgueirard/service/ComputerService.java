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
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		long id = 0;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			id = computerDAO.create(computer, connection);
			logDAO.create(id, dateSql.toString(), "create", connection);
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
	}

	public void update(Computer computer) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		long id = 0;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			id = computerDAO.update(computer, connection);
			logDAO.create(id, dateSql.toString(), "update", connection);
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
	}
	
	public void delete(String idString){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		long id = 0;
					
		try {
			
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			id = computerDAO.delete(idString, connection);
			logDAO.create(id, dateSql.toString(), "delete", connection);
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
	}
	
	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = dataBaseManager.getComputerDAO();
			CompanyService companyService = dataBaseManager.getCompanyService();
			LogDAO logDAO = dataBaseManager.getLogDAO();
			wrapper = computerDAO.retrieve(wrapper, companyService, connection);
			logDAO.create(0, dateSql.toString(), "retrieve", connection);
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
		return wrapper;
	}
}
