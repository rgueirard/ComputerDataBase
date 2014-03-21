package com.excilys.rgueirard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.excilys.rgueirard.domain.ComputerDTO;
import com.excilys.rgueirard.domain.PageWrapper;
import com.excilys.rgueirard.persistence.ComputerDAO;
import com.excilys.rgueirard.persistence.DataBaseManager;
import com.excilys.rgueirard.persistence.LogDAO;

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
	
	public void create(String name, String introducedDate,
			String discontinuedDate, String company) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computerDAO.create(name, introducedDate, discontinuedDate, company, connection);
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
	}

	public void update(String idS, String nameS, String introducedS,
			String discontinuedS, String companyIdS) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computerDAO.update(idS, nameS, introducedS, discontinuedS, companyIdS, connection);
			logDAO.create(dateSql.toString(), "update", connection);
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
					
		try {
			
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computerDAO.delete(idString, connection);
			logDAO.create(dateSql.toString(), "delete", connection);
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
	
	public PageWrapper<ComputerDTO> retrieve(PageWrapper<ComputerDTO> wrapper){
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
			logDAO.create(dateSql.toString(), "retrieve", connection);
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
