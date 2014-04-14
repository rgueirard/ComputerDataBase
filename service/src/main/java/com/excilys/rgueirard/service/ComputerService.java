package com.excilys.rgueirard.service;

import java.sql.SQLException;
import java.text.ParseException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.persistence.ComputerDAO;
import com.excilys.rgueirard.persistence.LogDAO;
import com.excilys.rgueirard.wrapper.PageWrapper;
import com.jolbox.bonecp.BoneCPDataSource;

@Service
@Transactional(readOnly = true)
public class ComputerService {

	private static Logger logger = LoggerFactory
			.getLogger(ComputerService.class);

	@Autowired
	private ComputerDAO computerDAO;

	@Autowired
	private LogService logService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private LogDAO logDAO;

	@Autowired
	private BoneCPDataSource dataBaseManager;

	public ComputerService() {
		super();
	}

	@Transactional(readOnly = false)
	public void create(Computer computer) {
		long id = 0;
		DateTime date = new DateTime();
		java.sql.Date dateSql = new java.sql.Date(date.getMillis());
		try {
			logger.info("ComputerService : creation d'ordinateur");
			id = computerDAO.create(computer);
			logDAO.create(id, dateSql.toString(), "create");
		} catch (SQLException e) {
			logger.error("erreur sql dans create");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error("erreur de parsage dans create");
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = false)
	public void update(Computer computer) {
		long id = 0;
		DateTime date = new DateTime();
		java.sql.Date dateSql = new java.sql.Date(date.getMillis());
		try {
			logger.info("ComputerService : edition d'ordinateur");
			id = computerDAO.update(computer);
			logDAO.create(id, dateSql.toString(), "update");
		} catch (SQLException e) {
			logger.error("erreur sql dans update");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error("erreur de parsage dans update");
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = false)
	public void delete(long pId) {
		long id = 0;
		DateTime date = new DateTime();
		java.sql.Date dateSql = new java.sql.Date(date.getMillis());
		try {
			logger.info("ComputerService : deletion d'ordinateur");
			id = computerDAO.delete(pId);
			logDAO.create(id, dateSql.toString(), "delete");
		} catch (SQLException e) {
			logger.error("erreur sql dans delete");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error("erreur de parsage dans delete");
			e.printStackTrace();
		}
	}

	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper) {
		try {
			logger.info("ComputerService : rechercher d'ordinateurs");
			wrapper = computerDAO.retrieve(wrapper);
		} catch (SQLException e) {
			logger.error("erreur Sql dans retrieve");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error("erreur parsage dans retrieve");
			e.printStackTrace();
		}
		return wrapper;
	}

	public Computer retrieveById(long id) {
		Computer computer = null;
		try {
			logger.info("ComputerService : rechercher d'ordinateur par id");
			computer = computerDAO.retrieveById(id);
		} catch (SQLException e) {
			logger.error("erreur Sql dans retrieve");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error("erreur parsage dans retrieve");
			e.printStackTrace();
		}
		return computer;
	}
}