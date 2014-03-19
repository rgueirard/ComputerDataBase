package com.excilys.rgueirard.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.excilys.rgueirard.domain.Computer;

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
	
	public Computer retrieve(String idS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		Computer computer = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computer = computerDAO.retrieve(idS, orderBy, connection);
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
		return computer;
	}
	
	public List<Computer> retrieveByName(String name, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		List<Computer> computers = null;
			
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computers = computerDAO.retrieveByName(name, orderBy, connection);
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
		return computers;
	}
	
	public List<Computer> retrieveByCompany(String companyName, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		List<Computer> computers = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computers = computerDAO.retrieveByCompany(companyName, orderBy, connection);
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
		return computers;
	}
	
	public List<Computer> retrieveByIntroduced(String introducedS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		List<Computer> computers = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computers = computerDAO.retrieveByIntroduced(introducedS, orderBy, connection);
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
		return computers;
			
	}
	
	public List<Computer> retrieveByDiscontinued(String discontinuedS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		List<Computer> computers = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computers = computerDAO.retrieveByDiscontinued(discontinuedS, orderBy, connection);
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
		return computers;
			
	}
	
	public List<Computer> retrieveAll(int orderBy, int offset, int nbDisplay) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		List<Computer> computers = null;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			computers = computerDAO.retrieveAll(orderBy, offset, nbDisplay, connection);
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
		return computers;
	}
	
	public int count(){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		int count = 0;
		
		try {
			connection.setAutoCommit(false);
			ComputerDAO computerDAO = ComputerDAO.getInstance();
			LogDAO logDAO = LogDAO.getInstance();
			count = computerDAO.count(connection);
			logDAO.create(dateSql.toString(), "count", connection);
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
		return count;
	}
}
