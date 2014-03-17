package com.excilys.rgueirard.persistence;

import java.sql.Connection;
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
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		computerDAO.create(name, introducedDate, discontinuedDate, company, connection);
	}

	public void update(String idS, String nameS, String introducedS,
			String discontinuedS, String companyIdS) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		computerDAO.update(idS, nameS, introducedS, discontinuedS, companyIdS, connection);
	}
	
	public void delete(String idString){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		computerDAO.delete(idString, connection);
	}
	
	public Computer retrieve(String idS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		return computerDAO.retrieve(idS, orderBy, connection);
	}
	
	public List<Computer> retrieveByName(String name, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		return computerDAO.retrieveByName(name, orderBy, connection);
	}
	
	public List<Computer> retrieveByCompany(String companyName, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		return computerDAO.retrieveByCompany(companyName, orderBy, connection);
	}
	
	public List<Computer> retrieveByIntroduced(String introducedS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		return computerDAO.retrieveByIntroduced(introducedS, orderBy, connection);
	}
	
	public List<Computer> retrieveByDiscontinued(String discontinuedS, int orderBy){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		return computerDAO.retrieveByDiscontinued(discontinuedS, orderBy, connection);
	}
	
	public List<Computer> retrieveAll(int orderBy, int offset, int nbDisplay) {
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		return computerDAO.retrieveAll(orderBy, offset, nbDisplay, connection);
	}
	
	public int count(){
		DataBaseManager dataBaseManager = DataBaseManager.getInstance();
		Connection connection = dataBaseManager.getConnection();
		ComputerDAO computerDAO = ComputerDAO.getInstance();
		return computerDAO.count(connection);
	}
}
