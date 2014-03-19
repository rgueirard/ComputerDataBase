package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.excilys.rgueirard.domain.ComputerWrapper;

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
		Connection connection = null;
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		
		try {
			connection = dataBaseManager.getConnection();
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
		Connection connection = null;
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		
		try {
			connection = dataBaseManager.getConnection();
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
	
	public ComputerWrapper retrieve(String idS, int orderBy, int offset, int nbDisplay){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		ComputerWrapper wrapper = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			wrapper = computerDAO.retrieve(idS, orderBy, offset, nbDisplay, connection);
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
	
	public ComputerWrapper retrieveByName(String name, int orderBy, int offset, int nbDisplay){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		ComputerWrapper wrapper = null;
			
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			wrapper = computerDAO.retrieveByName(name, orderBy, offset, nbDisplay, connection);
			logDAO.create(dateSql.toString(), "retrieveByName", connection);
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
	
	public ComputerWrapper retrieveByCompany(String companyName, int orderBy, int offset, int nbDisplay){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		ComputerWrapper wrapper = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			wrapper = computerDAO.retrieveByCompany(companyName, orderBy, offset, nbDisplay, connection);
			logDAO.create(dateSql.toString(), "retrieveByCompany", connection);
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
	
	public ComputerWrapper retrieveByIntroduced(String introducedS, int orderBy, int offset, int nbDisplay){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		ComputerWrapper wrapper = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			wrapper = computerDAO.retrieveByIntroduced(introducedS, orderBy, offset, nbDisplay, connection);
			logDAO.create(dateSql.toString(), "retrieveByIntroduced", connection);
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
	
	public ComputerWrapper retrieveByDiscontinued(String discontinuedS, int orderBy, int offset, int nbDisplay){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		ComputerWrapper wrapper = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			wrapper = computerDAO.retrieveByDiscontinued(discontinuedS, orderBy, offset, nbDisplay, connection);
			logDAO.create(dateSql.toString(), "retrieveByDiscontinued", connection);
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
	
	public ComputerWrapper retrieveAll(int orderBy, int offset, int nbDisplay) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		ComputerWrapper wrapper = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			wrapper = computerDAO.retrieveAll(orderBy, offset, nbDisplay, connection);
			logDAO.create(dateSql.toString(), "retrieveAll", connection);
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
