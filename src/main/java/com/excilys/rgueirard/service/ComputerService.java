package com.excilys.rgueirard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.persistence.ComputerDAO;
import com.excilys.rgueirard.persistence.DataBaseManager;
import com.excilys.rgueirard.persistence.LogDAO;
import com.excilys.rgueirard.wrapper.PageWrapper;

@Service
public class ComputerService {
	
	@Autowired
	private ComputerDAO computerDAO;
	
	@Autowired
	private LogDAO logDAO;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	DataBaseManager dataBaseManager;
	
	public ComputerService() {
		super();
	}
		
	public void create(Computer computer) {
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
	
	public void delete(long pId){
		Connection connection = null;
		Date dateUtil = new Date();
		java.sql.Date dateSql = new java.sql.Date(dateUtil.getTime());
		long id = 0;
					
		try {
			connection = dataBaseManager.getConnection();
			connection.setAutoCommit(false);
			id = computerDAO.delete(pId);
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
		try {
			wrapper = computerDAO.retrieve(wrapper, companyService);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally{
			try {dataBaseManager.closeConnection();}catch(SQLException e){e.printStackTrace();}
		}
		return wrapper;
	}
	
	public Computer retrieveById(long id) {
		Computer computer = null;
		try {
			computer = computerDAO.retrieveById(id, companyService);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally{
			try {dataBaseManager.closeConnection();}catch(SQLException e){e.printStackTrace();}
		}
		return computer;		
	}
}
