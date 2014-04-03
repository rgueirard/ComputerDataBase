package com.excilys.rgueirard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.rgueirard.domain.Computer;
import com.excilys.rgueirard.persistence.ComputerDAO;
import com.excilys.rgueirard.persistence.DataBaseManager;
import com.excilys.rgueirard.persistence.LogDAO;
import com.excilys.rgueirard.wrapper.PageWrapper;

@Service
public class ComputerService {

	private static Logger logger = LoggerFactory
			.getLogger(ComputerService.class);

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
			logger.debug("erreur sql dans create");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.debug("erreur de rollback dans create");
				e.printStackTrace();
			}
			e.printStackTrace();
		} catch (ParseException e) {
			logger.debug("erreur Parse dans create");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.debug("erreur de rollback dans create");
				e.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				dataBaseManager.closeConnection();
			} catch (SQLException e) {
				logger.debug("erreur de connectionClose dans create");
				e.printStackTrace();
			}
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
			logger.debug("erreur sql dans update");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.debug("erreur de rollback dans update");
				e.printStackTrace();
			}
			e.printStackTrace();
		} catch (ParseException e) {
			logger.debug("erreur de parsage dans update");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.debug("erreur de rollback dans update");
				e.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				dataBaseManager.closeConnection();
			} catch (SQLException e) {
				logger.debug("erreur de closeConnection dans update");
				e.printStackTrace();
			}
		}
	}

	public void delete(long pId) {
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
			logger.debug("erreur sql dans delete");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.debug("erreur rollback dans delete");
				e.printStackTrace();
			}
			e.printStackTrace();
		} catch (ParseException e) {
			logger.debug("erreur parsage dans delete");
			try {
				connection.rollback();
			} catch (SQLException e1) {
				logger.debug("erreur parsage dans delete");
				e.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				dataBaseManager.closeConnection();
			} catch (SQLException e) {
				logger.debug("erreur de closeConnection dans delete");
				e.printStackTrace();
			}
		}
	}

	public PageWrapper<Computer> retrieve(PageWrapper<Computer> wrapper) {
		try {
			wrapper = computerDAO.retrieve(wrapper, companyService);
		} catch (SQLException e) {
			logger.debug("erreur Sql dans retrieve");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.debug("erreur parsage dans retrieve");
			e.printStackTrace();
		} finally {
			try {
				dataBaseManager.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return wrapper;
	}

	public Computer retrieveById(long id) {
		Computer computer = null;
		try {
			computer = computerDAO.retrieveById(id, companyService);
		} catch (SQLException e) {
			logger.debug("erreur Sql dans retrieve");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.debug("erreur parsage dans retrieve");
			e.printStackTrace();
		} finally {
			try {
				dataBaseManager.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return computer;
	}
}
